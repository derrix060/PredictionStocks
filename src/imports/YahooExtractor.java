package imports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import types.Ticker;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * @author mario
 *
 */
public class YahooExtractor {
	
	public static Map <String, ArrayList<Ticker>> getHistorical (String[] ticker, Calendar from, Calendar to) throws IOException{
		
		Map<String, Stock> stocks = YahooFinance.get(ticker);
		Map <String, ArrayList<Ticker>> ret = new HashMap<String, ArrayList<Ticker>>();
		
		for (String symbol: stocks.keySet()){
			Stock stock = stocks.get(symbol);
			List<HistoricalQuote> historical = new ArrayList<>();
			ArrayList<Ticker> historicals = new ArrayList<>();
			
			historical = stock.getHistory(from, to, Interval.DAILY);
			
			for(HistoricalQuote quote : historical){
				Ticker tick = new Ticker();
				tick.setTicker(symbol);
				tick.setDate(quote.getDate());
				tick.setOpenPrice(quote.getOpen().doubleValue());
				tick.setHighPrice(quote.getHigh().doubleValue());
				tick.setLowPrice(quote.getLow().doubleValue());
				tick.setClosePrice(quote.getAdjClose().doubleValue());
				tick.setVolume(quote.getVolume().doubleValue());
				
				historicals.add(tick);
			}
			
			ret.put(symbol, historicals);
		}
		
		return ret;
	}
	
}
