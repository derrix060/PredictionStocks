package types;

import java.util.ArrayList;

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
	
	public static HistoricalData createNNHistoricalData(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalize normal){
		int size = normalizedData.size - normalizedData.getDateInterval();
		HistoricalData rtn = new HistoricalData(size);
		Data dt = new Data();
		ArrayList<Data> datas = new ArrayList<>();
		
		double[][] input = normalizedData.toInput(attr);
		double[] calculatedData = new double[attr.size()];
		double value = 0;
		
		
		for (int i=normalizedData.getDateInterval(); i<normalizedData.size; i++){
			//for each date
			
			dt = new Data();
			dt.setAttributes(attr);
			dt.setTicker(normalizedData.getMapHistorical().get(0).getTicker());
			
			
			network.compute(input[i - normalizedData.getDateInterval()], calculatedData); //generate the calculatedData
			
			Data dtClone = normalizedData.getMapHistorical().get(i);
			dt.setDate(dtClone.getDate());
			
			for(int j=0; j<attr.size(); j++){
				//for each atribute
				
				//Check: every time will be in order?
				value = normal.getDenormalizedValueFrom(calculatedData[j]);
				dt.setValue(attr.get(j), value);
			}
			
			
			datas.add(dt);
		}
		
		rtn.setMapHistorical(datas);
		
		return rtn;
	}
}
