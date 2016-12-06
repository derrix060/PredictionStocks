package imports;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import types.Ticker;

public class TrainingFactory {
	private String ticker;
	private Calendar from;
	private Calendar to;
	
	//qty atributes
	public static final int qtyAtrInput = 3; 	//closePrice, highPrice, lowPrice, openPrice, volume
	public static final int qtyAtrOutput = 3; 	//closePrice, highPrice, lowPrice
	
	//constructor
	public TrainingFactory(String ticker, Calendar from, Calendar to) {
		this.ticker = ticker;
		this.from = from;
		this.to = to;
	}

	
	public double[][] getInput(float margin, double minValue, double maxValue) throws IOException{		
		HashSet<Ticker> historical = YahooExtractor.getHistorical(ticker, from, to);
		Normalize.normalizeValues(historical, minValue, maxValue, margin);
		double[][] resp = new double[historical.size()][qtyAtrInput];
		
		int i=0;
		for (Ticker t : historical){
			resp[i][0] = t.getClosePrice();
			resp[i][1] = t.getHighPrice();
			resp[i][2] = t.getLowPrice();
			//resp[i][3] = t.getOpenPrice();
			//resp[i][4] = t.getVolume();
			
			i++;
		}
		return resp;
	}

	
	public double[][] getInput(float margin) throws IOException{		
		
		return getInput(margin, 0, 0);
	}
	
	public double[][] getIdealOutput(float margin, double minValue, double maxValue) throws IOException{
		Calendar newTo = to;
		Calendar newFrom = from;
		
		newTo.add(Calendar.DAY_OF_MONTH, 1); //correct This
		newFrom.add(Calendar.DAY_OF_MONTH, 1); //correct This
		
		
		HashSet<Ticker> historical = YahooExtractor.getHistorical(ticker, newFrom, newTo);
		Normalize.normalizeValues(historical, minValue, maxValue, margin);
		double[][] resp = new double[historical.size()][qtyAtrOutput];
		//ArrayList<Ticker> historicalArray = new ArrayList<Ticker> (historical);
		
		int i = 0;
		
		for (Ticker t : historical){
			resp [i][0] = t.getClosePrice();
			resp[i][1] = t.getHighPrice();
			resp[i][2] = t.getLowPrice();
			i ++;
		}
		return resp;		
	}
	
	
	//getters and setters
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Calendar getFrom() {
		return from;
	}

	public void setFrom(Calendar from) {
		this.from = from;
	}

	public Calendar getTo() {
		return to;
	}

	public void setTo(Calendar to) {
		this.to = to;
	}
	
	
	
}
