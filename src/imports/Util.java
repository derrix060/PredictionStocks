/**
 * 
 */
package imports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import types.Ticker;

/**
 * @author mario
 *
 */
public class Util {

	public static Calendar getNextDay(Calendar date, String ticker) throws IOException{
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
}
