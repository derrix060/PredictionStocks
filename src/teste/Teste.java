package teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import imports.YahooExtractor;
import process.Normalize;
import process.PropagationFactory.trainingType;
import types.Ticker;

public class Teste {
	
	public static void toDo(){
		//dropout -> protect the overfitting
		//bias -> permit a better adaptation, increesing the freedom.
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//testeCamadas();
		testeNormalize();
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
		
		Map <String, ArrayList<Ticker>> historicals = YahooExtractor.getHistorical(ticker, from, to);
		
		for (String symbol : historicals.keySet()){
			ArrayList<Ticker> tickers = new ArrayList<>();
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
