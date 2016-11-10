package teste;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.encog.mathutil.randomize.ConsistentRandomizer;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.XOR;
import org.encog.neural.networks.structure.NetworkCODEC;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.manhattan.ManhattanPropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.simple.EncogUtility;

import imports.Normalize;
import imports.TrainingFactory;
import imports.YahooExtractor;
import process.NetworkFactory;
import process.ActivationFunctionFactory.enumActivationFuncion;
import types.Ticker;

@SuppressWarnings("unused")
public class Teste {
	
	static Calendar from = Calendar.getInstance();
	static Calendar to = Calendar.getInstance();
	static String ticker = "PETR4.SA";
	static int maxIteration = 5000;
	public static void main(String[] args) throws IOException {
		to.add(Calendar.DATE,-2);
		from.add(Calendar.DATE, -23);
		//testCompleteTrain();
		//testeNetwork();
		//testeCamadas();
		//testeNormalize();
		testeTrain(false);
		
	}
	
	
	
	public static Calendar testeNextDay(Calendar date, String ticker) throws IOException{
		int size = 0;
		Calendar resp = Calendar.getInstance();
		Date dt = date.getTime();
		resp.setTime(dt);
		
		while(size == 0){
			try{
				resp.add(Calendar.DAY_OF_MONTH, 1);
				HashSet<Ticker> historico = YahooExtractor.getHistorical(ticker, resp, resp);
				size = historico.size();
			}
			catch(FileNotFoundException e){
				System.err.println("Não existe dados ainda!");
				return null;
			}
		}
		
		return resp;
	}
	
	public static void testeNetwork() throws IOException{
		BasicNetwork network = getTesteNetwork();
		
		for(int i=0; i<network.getLayerCount(); i++){
			System.out.println("Camada " + i + ", qtde neuronios: " + network.getLayerNeuronCount(i) + ", total: " + network.getLayerTotalNeuronCount(i));
		}
	}
	
	public static BasicNetwork getTesteNetwork() throws IOException{
		return new NetworkFactory().getNetwork(testeCamadas(), false, 0.5, enumActivationFuncion.Sigmoid);
		
	}
	
	public static List<Integer> testeCamadas(){
		String entrada = "5,2,1";
		List<String> camadasStr = Arrays.asList(entrada.replaceAll(" ", "").split(","));
		List<Integer> camadas = new ArrayList<>();
		
		camadasStr.forEach(cam -> camadas.add(new Integer(cam)));
		return camadas;
	}
	
	public static void testeNormalize() throws IOException{
		String[] tickers = {ticker, "DTEX3.SA"};

		from.add(Calendar.DAY_OF_MONTH, -8);
		to.add(Calendar.DAY_OF_MONTH, -1);
		
		Map <String, HashSet<Ticker>> historicals = YahooExtractor.getHistorical(tickers, from, to);
		
		for (String symbol : historicals.keySet()){
			HashSet<Ticker> hashTickers = new HashSet<>();
			hashTickers = historicals.get(symbol);
			
			System.out.println("\nTicker: " + symbol);
			System.out.println("Before");
			for (Ticker tick : hashTickers){
				System.out.println(tick);
			}
			
			Normalize.normalizeValues(hashTickers);
			
			System.out.println("After");
			for (Ticker tick : hashTickers){
				System.out.println(tick);
			}
		}
	}
	
	public static void testCompleteTrain()
	{
		MLDataSet trainingData = new BasicMLDataSet(XOR.XOR_INPUT,XOR.XOR_IDEAL);
		
		BasicNetwork network = EncogUtility.simpleFeedForward(2, 5, 7, 1, true);
		
		MLTrain rprop = new ResilientPropagation(network, trainingData);
		
		double[] weights1 = NetworkCODEC.networkToArray(network);
		System.out.println("Peso antes!");
		for (int i=0; i<weights1.length; i++)
			System.out.println("Neuronio " + i + ", peso: " + weights1[i]);
		
		int iteration = 0;
		do {
			rprop.iteration();
			iteration++;
		} while( iteration<5000 && rprop.getError()>0.01);
		

		System.out.println("Peso depois!");
		for (int i=0; i<weights1.length; i++)
			System.out.println("Neuronio " + i + ", peso: " + weights1[i]);
	}

	public static void testeTrain(boolean verbose) throws IOException{
		BasicNetwork network = getTesteNetwork();
		
		from.add(Calendar.DAY_OF_MONTH, -12);
		to.add(Calendar.DAY_OF_MONTH, -5);
		TrainingFactory training = new TrainingFactory(ticker, from, to);
		
		//input
		double[][] input = training.getInput(0.4f);
		
		if (verbose){
			System.out.println("Input");
			for (int i = 0; i< input.length; i++){
				for (int j=0; j< 4; j++){
					System.out.println("i: " + i + " j: " + j + " value: " + input[i][j]);
				}
			}
		}
		
		//output
		double[][] output = training.getIdealOutput(0.4f);
		
		if(verbose){
			System.out.println("Output");
			for (int i=0; i<output.length; i++)
				System.out.println("i: " + i + " value: " + output[i][0]);
		}
		
		//treinamento
		MLDataSet trainingData = new BasicMLDataSet(input, output);
		MLTrain rprop = new Backpropagation(network, trainingData);
		
		double[] weights1 = NetworkCODEC.networkToArray(network);
		
		if (verbose){
			System.out.println("Peso antes!");
			for (int i=0; i<weights1.length; i++)
				System.out.println("Ligacao " + i + ", peso: " + weights1[i]);
		}
		
		System.out.println("Começo do treinamento da rede!!");
		
		int iteration = 0;
		do{
			rprop.iteration();
			iteration ++;
		}
		while(iteration < maxIteration && rprop.getError() > 0.01);
		
		if(verbose){
			System.out.println("Peso depois!");
			for (int i=0; i<weights1.length; i++)
				System.out.println("Ligacao " + i + ", peso: " + weights1[i]);
		}
		System.out.println("Rede treinada!\nIteracoes: " + iteration +"\nErro: " + rprop.getError());
		
		
		System.out.println("Validacao!");
		
		
	}
}
