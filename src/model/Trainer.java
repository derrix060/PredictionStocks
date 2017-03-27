package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InvalidPropertiesFormatException;

import javax.naming.directory.InvalidAttributeValueException;

import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;

import factories.PropagationFactory;
import factories.PropagationFactory.enumTrainingType;
import model.Data.enumAttributesOfData;

public class Trainer {
	private Normalizer normal;
	
	public Trainer(float margin, double inferiorLimit, double superiorLimit){
		this.normal = new Normalizer(margin, superiorLimit, inferiorLimit);
		
	}
	
	/**
	 * Train the network
	 * @param network - The network to be trained
	 * @param rule - Wich rule will be use to train (eg: Backpropagation)
	 * @param maxIteration - Max number of how many times it will be iterate
	 * @param maxError - Max number of error to stop train
	 * @param from - Date to start train
	 * @param to - Date to stop train
	 * @throws IOException When cannot create new Historical Data
	 * @throws InvalidPropertiesFormatException When dates is wrong!
	 * @throws InvalidAttributeValueException When data lengh is smaller than dateInterval
	 */
	public ArrayList<Double> train(NeuralNetwork network, enumTrainingType rule, int maxIteration, double maxError, Calendar from, Calendar to) throws IOException, InvalidPropertiesFormatException, InvalidAttributeValueException{
		ArrayList<Double> errors = new ArrayList<>();
		
		if (from.after(to)) throw new InvalidPropertiesFormatException("'From' date must be befor than 'to' date!");
		HistoricalData hd = new HistoricalData(network.getStock(), from, to, network.getDateInterval(), network.getAttributes());
		
		if(hd.size < hd.getDateInterval()) throw new InvalidAttributeValueException("Data lengh is smaller than NN's dateInterval");
		
		normal.normalizeDatas(hd);
		
		BasicMLDataSet trainingData = new BasicMLDataSet(hd.toInput(network.getAttributes()), hd.toIdealOutput(network.getAttributes()));		
		
		MLTrain rprop = PropagationFactory.create(rule, network.getTopology(), trainingData, PropagationFactory.DEFAULT_TRAINING_RATE);
		
		
		int iteration = 0;
		
		do{
			rprop.iteration();
			iteration ++;
			errors.add(rprop.getError());
			System.out.println("Epoch #" + iteration + " Error:"+ rprop.getError());
		}
		while (iteration < maxIteration && rprop.getError() > maxError);

		
		// Need call this because of multithread
		rprop.finishTraining();
		

		System.out.println("Rede treinada!\nIteracoes: " + iteration +"\nErro: " + rprop.getError());
		

		//print Teste
		Trainer.printTeste(network.getTopology(), hd, network.getAttributes(), normal);
		
		return errors;
	}
	
	

	//TODO: remove
	public static void train(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, enumTrainingType rule, int maxIteration, double maxError, Normalizer normal){
		BasicMLDataSet trainingData = new BasicMLDataSet(normalizedData.toInput(attr), normalizedData.toIdealOutput(attr));
		MLTrain rprop = PropagationFactory.create(rule, network, trainingData, PropagationFactory.DEFAULT_TRAINING_RATE);

		int iteration = 0;

		do{
			rprop.iteration();
			iteration ++;
		}
		while (iteration < maxIteration && rprop.getError() > maxError);

		/**
		 * Should be called once training is complete and no more iterations are needed.
		 *  Calling iteration again will simply begin the training again,
		 *  and require finishTraining to be called once the new training session is complete.
		 *  It is particularly important to call finishTraining for multithreaded
		 *  training techniques.
		 */
		rprop.finishTraining();

		System.out.println("Rede treinada!\nIteracoes: " + iteration +"\nErro: " + rprop.getError());

		//print Teste
		Trainer.printTeste(network, normalizedData, attr, normal);

	}

	public static void printTeste(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalizer normal){
		double[][] input = normalizedData.toInput(attr);
		double[][] idealOutput = normalizedData.toIdealOutput(attr);

		double[] output = new double[attr.size()];

		for (int i=0; i<input.length; i++){
			//for each data

			network.compute(input[i], output);

				System.out.println("Data " + i + "->");
				System.out.println("ideal: ");
				for (int j=0; j<attr.size(); j++)
					System.out.println(normal.getDenormalizedValueFrom(idealOutput[i][j]));

				System.out.println();

				System.out.println("output: ");
				for (int j=0; j<attr.size(); j++)
					System.out.println(normal.getDenormalizedValueFrom(output[j]));

				System.out.println("---------------------------------\n");

		}

	}

	public static HistoricalData createNNHistoricalData(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalizer normal, Calendar from){
		int size = normalizedData.size - normalizedData.getDateInterval();
		HistoricalData rtn = new HistoricalData(size);
		Data dt = new Data();
		ArrayList<Data> datas = new ArrayList<>();

		int i = 0;
		int dateInterval = normalizedData.getDateInterval();
		int attrSize = attr.size();

		double[] input = new double[attr.size() * dateInterval];
		double[] calculatedData = new double[attr.size()];
		double value = 0;

		while (normalizedData.getMapHistorical().get(i).getDate().compareTo(from) < 0){
			i ++;
		}
		int dtDif = i - 1; //start of day adjusted


		//first dateInterval times will have data from real (mixed datas)
		for (int t=0; t<dateInterval; t++){
			//for each time until dateInterval times


			//add real data
			for (int d=0; d<dateInterval-t; d++){
				for (int a=0; a<attrSize; a++){
					//for each attributee

					input[(d  * attrSize) + a] = normalizedData.getMapHistorical().get(dtDif + t + d - dateInterval).getValue(attr.get(a));
				}

			}

			//add calculate data
			for (int d=0; d<t; d++){
				for (int a=0; a<attrSize; a++){
					//for each attribute

					input[((dateInterval - t + d) * attrSize) + a] = datas.get(d).getValue(attr.get(a));
				}

			}
			//now input is ok


			dt = new Data();
			dt.setAttributes(attr);
			dt.setTicker(normalizedData.getMapHistorical().get(0).getTicker());

			network.compute(input, calculatedData);
			Data dtClone = normalizedData.getMapHistorical().get(dtDif + t);
			dt.setDate(dtClone.getDate());

			for(int a=0; a<attr.size(); a++){
				//for each atribute

				//Check: every time will be in order?
				value = calculatedData[a];
				dt.setValue(attr.get(a), value);
			}


			datas.add(dt);
		}
		//end of mixed datas

		//now only calculated
		for (i = dtDif + dateInterval; i<normalizedData.size; i++){
			//for each date

			dt = new Data();
			dt.setAttributes(attr);
			dt.setTicker(normalizedData.getMapHistorical().get(0).getTicker());
			Data dtClone = normalizedData.getMapHistorical().get(i);
			dt.setDate(dtClone.getDate());

			//create input
			for (int d=0; d<dateInterval; d++){
				//for each date

				for (int a=0; a<attrSize; a++){
					//for each attribute

					input[(d * attrSize) + a] = datas.get(i - dtDif - dateInterval + d).getValue(attr.get(a));
				}
			}

			network.compute(input, calculatedData); //generate the calculatedData


			for(int a=0; a<attr.size(); a++){
				//for each atribute

				//Check: every time will be in order?
				value = calculatedData[a];
				dt.setValue(attr.get(a), value);
			}


			datas.add(dt);
		}

		rtn.setMapHistorical(datas);

		//denormalizeValues
		normal.denormalizeDatas(rtn);

		return rtn;
	}
}
