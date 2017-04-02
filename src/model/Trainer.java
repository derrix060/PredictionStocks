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
		printTeste(network.getTopology(), hd, network.getAttributes(), normal);
		
		return errors;
	}
	

	//TODO: remove
	private void printTeste(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalizer normal){
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

}
