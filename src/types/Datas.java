package types;

import java.util.Arrays;
import java.util.Set;
import imports.Normalize;

public class Datas {
	
	public static final int qtyAtr = 3;

	private double maxValue;
	private double minValue;
	private double maxNormalizedValue;
	private double minNormalizedValue;
	private float margin;
	private double[][] values;
	private double[][] normalizedValues;
	
	
	public Datas(Set<Ticker> tickers, float margin) {
		setValues(tickerToDouble(tickers));
		setMaxValue(Arrays.stream(getValues()).flatMapToDouble(Arrays::stream).max().getAsDouble());
		setMinValue(Arrays.stream(getValues()).flatMapToDouble(Arrays::stream).min().getAsDouble());
		
		updateNormalized();
	}
	
	public void updateNormalized(){
		Normalize.normalizeValues(this);
		setMaxNormalizedValue(Arrays.stream(getNormalizedValues()).flatMapToDouble(Arrays::stream).max().getAsDouble());
		setMinNormalizedValue(Arrays.stream(getNormalizedValues()).flatMapToDouble(Arrays::stream).min().getAsDouble());
		
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
	
}
