package teste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import imports.YahooExtractor;
import process.Normalize;
import types.Ticker;

public class Teste {
	
	public static void main(String[] args) throws IOException {
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
				System.out.println("Ticker: " + tick.getTicker() + " Normalized value: " + tick.getNormalizedValue());
			}
			
			Normalize.normalizeValues(tickers);
			
			System.out.println("After");
			for (Ticker tick : tickers){
				System.out.println("Ticker: " + tick.getTicker() + " Normalized value: " + tick.getNormalizedValue());
			}
		}
	}
}
