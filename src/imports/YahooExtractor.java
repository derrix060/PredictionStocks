package imports;

/**
 * @author mario
 *
 */
public class YahooExtractor {
	
	
	public static float getStock(String ticker, Calendar date){
		
		
		try {
			retorno = YahooFinance.get(tickerToYahoo(ticker)).getHistory(date, date, Interval.DAILY).get(0).getClose().floatValue();
			
			
		} catch (IOException e) {
			System.err.println("Error to get data from Yahoo. Method YahooExtractor.getCotacao");
			e.printStackTrace();
		}
	}
}
