package types;

import java.util.ArrayList;
import java.util.Calendar;

import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;

import imports.Normalize;
import process.PropagationFactory;
import process.PropagationFactory.enumTrainingType;
import types.Data.enumAttributesOfData;

public class Trainer {
	private final static double default_trainingRate = 0.1;
	
	public static void train(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, enumTrainingType rule, int maxIteration, double minError, Normalize normal){
		BasicMLDataSet trainingData = new BasicMLDataSet(normalizedData.toInput(attr), normalizedData.toIdealOutput(attr));
		MLTrain rprop = PropagationFactory.create(rule, network, trainingData, default_trainingRate);
		
		int iteration = 0;
		
		do{
			rprop.iteration();
			iteration ++;
		}
		while (iteration < maxIteration && rprop.getError() > minError);
		
		System.out.println("Rede treinada!\nIteracoes: " + iteration +"\nErro: " + rprop.getError());
		
		//print Teste
		Trainer.printTeste(network, normalizedData, attr, normal);
		
	}
	
	public static void printTeste(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalize normal){
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
	
	public static HistoricalData createNNHistoricalData(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalize normal, Calendar from){
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
