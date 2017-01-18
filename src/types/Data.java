package types;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import imports.Normalize;
import imports.YahooExtractor;

public class Data {
	
	public static final int qtyAtr = 3;

	private double maxValue;
	private double minValue;
	private double maxNormalizedValue;
	private double minNormalizedValue;
	private float margin;
	private double[][] values;
	private double[][] normalizedValues;
	private String ticker;
	private Calendar from;
	private Calendar to;
	
	public enum enumStockAttribute{
		ClosePrice, HighPrice, LowPrice, OpenPrice, Volume;
	}
	
	//TODO: implementar entrada com Data antiga input
	public Data(String ticker, Calendar from, Calendar to, float margin, double maxValue, double minValue, double maxNormalizedValue, double minNormalizedValue, boolean isIdealOutput) throws IOException{
		setFrom(from);
		setTo(to);
		setMargin(margin);

		
		HashSet<Ticker> tickers = YahooExtractor.getHistorical(ticker, from, to);
		
		setValues(tickerToDouble(tickers));
		
		if (!isIdealOutput){
			setMaxValue(Arrays.stream(getValues()).flatMapToDouble(Arrays::stream).max().getAsDouble());
			setMinValue(Arrays.stream(getValues()).flatMapToDouble(Arrays::stream).min().getAsDouble());
		}
		else{
			setMaxValue(maxValue);
			setMinValue(minValue);
			setMaxNormalizedValue(maxNormalizedValue);
			setMinNormalizedValue(minNormalizedValue);
		}
		
		normalizeValues();
		
		for (double[] atr : getNormalizedValues()){
			for (double value : atr){
				System.out.println("Norm value: " + value);
			}
		}
		
		if (!isIdealOutput){
			setMaxNormalizedValue(Arrays.stream(getNormalizedValues()).flatMapToDouble(Arrays::stream).max().getAsDouble());
			setMinNormalizedValue(Arrays.stream(getNormalizedValues()).flatMapToDouble(Arrays::stream).min().getAsDouble());
		}
		
		
		
		

		
	}
	public Data(String ticker, Calendar from, Calendar to, float margin) throws IOException{
		this(ticker, from, to, margin, 0, 0, 0, 0, false);
	}
	
	private double[][] tickerToDouble (Set<Ticker> tickers){
		double[][] rtn = new double[tickers.size()][qtyAtr];
		
		int i=0;
		
		for (Ticker t : tickers){
			rtn[i][0] = t.getClosePrice();
			rtn[i][1] = t.getHighPrice();
			rtn[i][2] = t.getLowPrice();
			//resp[i][3] = t.getOpenPrice();
			//resp[i][4] = t.getVolume();
			
			i++;
		}
		
		return rtn;
	}
	
	public void normalizeValues() throws IOException{
		setNormalizedValues(Normalize.normalizeValues(this));
	}
	
	
	//Getters and setters
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	public double getMaxNormalizedValue() {
		return maxNormalizedValue;
	}
	public void setMaxNormalizedValue(double maxNormalizedValue) {
		this.maxNormalizedValue = maxNormalizedValue;
	}
	public double getMinNormalizedValue() {
		return minNormalizedValue;
	}
	public void setMinNormalizedValue(double minNormalizedValue) {
		this.minNormalizedValue = minNormalizedValue;
	}
	public double[][] getValues() {
		return values;
	}
	public void setValues(double[][] values) {
		this.values = values;
	}
	public double[][] getNormalizedValues() {
		return normalizedValues;
	}
	public void setNormalizedValues(double[][] normalizedValues) {
		this.normalizedValues = normalizedValues;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

	public Calendar getFrom() {
		return from;
	}

	public void setFrom(Calendar from) {
		this.from = from;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Calendar getTo() {
		return to;
	}

	public void setTo(Calendar to) {
		this.to = to;
	}
	
}
