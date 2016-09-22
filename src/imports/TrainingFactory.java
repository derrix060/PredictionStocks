package imports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.stream.Stream;

import types.Ticker;

public class TrainingFactory {
	private String ticker;
	private Calendar from;
	private Calendar to;
	
	//qty atributes
	private final int qtyAtr = 5; //closePrice, highPrice, lowPrice, openPrice, volume
	
	//constructor
	public TrainingFactory(String ticker, Calendar from, Calendar to) {
		this.ticker = ticker;
		this.from = from;
		this.to = to;
	}

	
	public double[][] getInput(float margin) throws IOException{		
		HashSet<Ticker> historical = YahooExtractor.getHistorical(ticker, from, to);
		Normalize.normalizeValues(historical, margin);
		double[][] resp = new double[historical.size()][qtyAtr];
		
		int i=0;
		for (Ticker t : historical){
			resp[i][0] = t.getClosePrice();
			resp[i][1] = t.getHighPrice();
			resp[i][2] = t.getLowPrice();
			resp[i][3] = t.getOpenPrice();
			resp[i][4] = t.getVolume();
			
			i++;
		}
		return resp;
	}
	
	public double[][] getOutput(float margin) throws IOException{
		Calendar newTo = to;
		Calendar newFrom = from;
		
		newTo.add(Calendar.DAY_OF_MONTH, 1); //correct This
		newFrom.add(Calendar.DAY_OF_MONTH, 1); //correct This
		
		
		HashSet<Ticker> historical = YahooExtractor.getHistorical(ticker, newFrom, newTo);
		Normalize.normalizeValues(historical, margin);
		double[][] resp = new double[historical.size()][1];
		ArrayList<Ticker> historicalArray = new ArrayList<Ticker> (historical);
		
		for(int i=0;i<historical.size();i++)
		
		
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
