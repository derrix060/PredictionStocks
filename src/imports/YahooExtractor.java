package imports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import types.Data;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * @author mario
 *
 */
public class YahooExtractor {
	
	public static Map <String, HashSet<Data>> getHistorical (String[] ticker, Calendar from, Calendar to) throws IOException{
		
		Map<String, Stock> stocks = YahooFinance.get(ticker);
		Map<String, HashSet<Data>> ret = new HashMap<String, HashSet<Data>>();
		
		for (String symbol: stocks.keySet()){
			HashSet<Data> historicals = getHistorical(symbol, from, to);
			ret.put(symbol, historicals);
		}
		
		return ret;
	}
	
	public static HashSet<Data> getHistorical(String ticker, Calendar from, Calendar to) throws IOException{
		Stock stock = YahooFinance.get(ticker);
		
		List<HistoricalQuote> historical = new ArrayList<>();
		HashSet<Data> historicals = new HashSet<>();
		
		historical = stock.getHistory(from, to, Interval.DAILY);
		
		for(HistoricalQuote quote : historical){
			Data tick = new Data();
			tick.setTicker(ticker);
			tick.setDate(quote.getDate());
			tick.setOpenPrice(quote.getOpen().doubleValue());
			tick.setHighPrice(quote.getHigh().doubleValue());
			tick.setLowPrice(quote.getLow().doubleValue());
			tick.setClosePrice(quote.getAdjClose().doubleValue());
			tick.setVolume(quote.getVolume().doubleValue());
			
			historicals.add(tick);
		}
		
		
		return historicals;
	}
	
}
