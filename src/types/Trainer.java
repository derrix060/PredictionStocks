package types;

import java.util.ArrayList;

import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;

import process.PropagationFactory;
import process.PropagationFactory.enumTrainingType;
import types.Data.enumAttributesOfData;

public class Trainer {
	private final static double default_trainingRate = 0.1;
	
	public static void train(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, enumTrainingType rule, int maxIteration, double minError){
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
		Trainer.printTeste(network, normalizedData, attr);
		
	}
	
	public static void printTeste(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr){
		double[][] input = normalizedData.toInput(attr);
		double[][] idealOutput = normalizedData.toIdealOutput(attr);
		
		double[] output = new double[input.length];
		for (int i=0; i<input.length; i++){
			//for each data
			
			network.compute(input[i], output);
			
				System.out.println("Data " + i + "-> output: " + output + " | ideal: " + idealOutput[i]);
			
		}
		
	}
}
