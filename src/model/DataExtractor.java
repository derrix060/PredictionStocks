package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Data.enumAttributesOfData;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * Data extractor from stocks markets
 * @author mario
 */
public class DataExtractor {
	
	public static  ArrayList<Data> getHistorical(String ticker, Calendar from, Calendar to, List<enumAttributesOfData> attributes) throws IOException{
		
		Stock stock = YahooFinance.get(ticker);
		
		List<HistoricalQuote> historical = new ArrayList<>();
		ArrayList<Data> historicals = new ArrayList<>();
		
		historical = stock.getHistory(from, to, Interval.DAILY);
		
		for(HistoricalQuote quote : historical){
			Data tick = new Data();
			tick.setTicker(ticker);
			tick.setDate(quote.getDate());
			
			try{
				for(enumAttributesOfData atr : attributes){
					switch (atr) {
					case openPrice:
						tick.setOpenPrice(quote.getOpen().doubleValue());
						break;
					case closePrice:
						tick.setClosePrice(quote.getAdjClose().doubleValue());
						break;
					case highPrice:
						tick.setHighPrice(quote.getHigh().doubleValue());
						break;
					case lowPrice:
						tick.setLowPrice(quote.getLow().doubleValue());
						break;
					case volume:
						tick.setVolume(quote.getVolume().doubleValue());
						break;
					}
					
				}
				historicals.add(tick);
			}
			catch (NullPointerException e){
				System.out.println("Didn't find values from " + tick.getTicker() + ", " + tick.getDate());
			}
		}
		
		//order by date
		historicals.sort((Data d1, Data d2) -> d1.getDate().compareTo(d2.getDate()));
		
		
		return historicals;
	}
	
}
