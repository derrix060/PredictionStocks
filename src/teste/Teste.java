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
import imports.YahooExtractor;
import process.NetworkFactory;
import process.ActivationFunctionFactory.enumActivationFuncion;
import types.Ticker;

@SuppressWarnings("unused")
public class Teste {
	
	public static void main(String[] args) throws IOException {
		//testeCamadas();
		testeNormalize();
	}
	
	public static void testeNetwork(){
		BasicNetwork network = new NetworkFactory().getNetwork(testeCamadas(), true, 0.5, enumActivationFuncion.Sigmoid);
		
		//MLDataSet trainingSet = new BasicMLDataSet(input, ideal)
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
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();

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
