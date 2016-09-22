package teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;

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
	
	public static void main(String[] args) throws IOException {
		Calendar dt = Calendar.getInstance();
		dt.add(Calendar.DAY_OF_MONTH, -4);
		Calendar data = testeNextDay(dt);
		System.out.println(data);
		//testeNetwork();
		//testeCamadas();
		//testeNormalize();
	}
	
	public static Calendar testeNextDay(Calendar date) throws IOException{
		
			try{
				HashSet<Ticker> historico = YahooExtractor.getHistorical("ABCB4.SA", date, date);
			}
			catch(IOException e){
				//not found
				date.add(Calendar.DAY_OF_MONTH, 1);
				System.out.println("teste date: " + date);
				return testeNextDay(date);
			}
			
			return date;
	}
	
	
	public static void testeNetwork() throws IOException{
		BasicNetwork network = new NetworkFactory().getNetwork(testeCamadas(), true, 0.5, enumActivationFuncion.Sigmoid);
		
		

		from.add(Calendar.DAY_OF_MONTH, -12);
		to.add(Calendar.DAY_OF_MONTH, -5);
		TrainingFactory training = new TrainingFactory("PETR4.SA", from, to);
		double[][] input = training.getInput(0.4f);
		
		for (int i = 0; i< input.length; i++){
			for (int j=0; j< 4; j++){
				System.out.println("i: " + i + " j: " + j + " value: " + input[i][j]);
			}
		}
		
		System.out.println("Output");
		double[][] output = training.getOutput(0.4f);
		for (int i=0; i<output.length; i++)
			System.out.println("i: " + i + " value: " + output[i][0]);
		
		
	}
	
	public static Set<Integer> testeCamadas(){
		String entrada = "1, 2,3,4,5, 6,7,8,9,10";
		Set<String> camadasStr = new HashSet<>();
		Set<Integer> camadas = new HashSet<>();
		
		camadasStr.addAll(Arrays.asList(entrada.replaceAll(" ", "").split(",")));
		
		camadasStr.forEach(qtde -> camadas.add(new Integer(qtde)));
		return camadas;
	}
	
	public static void testeNormalize() throws IOException{
		String[] ticker = {"ABCB4.SA", "DTEX3.SA"};

		from.add(Calendar.DAY_OF_MONTH, -8);
		to.add(Calendar.DAY_OF_MONTH, -1);
		
		Map <String, HashSet<Ticker>> historicals = YahooExtractor.getHistorical(ticker, from, to);
		
		for (String symbol : historicals.keySet()){
			HashSet<Ticker> tickers = new HashSet<>();
			tickers = historicals.get(symbol);
			
			System.out.println("\nTicker: " + symbol);
			System.out.println("Before");
			for (Ticker tick : tickers){
				System.out.println(tick);
			}
			
			Normalize.normalizeValues(tickers);
			
			System.out.println("After");
			for (Ticker tick : tickers){
				System.out.println(tick);
			}
		}
	}
}
