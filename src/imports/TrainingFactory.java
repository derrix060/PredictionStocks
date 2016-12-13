package imports;

import java.io.IOException;
import java.util.Calendar;

import types.Data;

public class TrainingFactory {
	private String ticker;
	private Calendar from;
	private Calendar to;
	private float margin;
	private double maxValue;
	private double minValue;
	private double maxNormalizedValue;
	private double minNormalizedValue;
		
	//constructor
	public TrainingFactory(String ticker, Calendar from, Calendar to, float margin) {
		setTicker(ticker);
		setFrom(from);
		setTo(to);
		setMargin(margin);
	}

	public Data getInput() throws IOException{
		Data resp = new Data(getTicker(),getFrom(), getTo(), getMargin());
		
		setMaxValue(resp.getMaxValue());
		setMinValue(resp.getMinValue());
		setMaxNormalizedValue(resp.getMaxNormalizedValue());
		setMinNormalizedValue(resp.getMinNormalizedValue());
		
		return resp;
	}
	
	public Data getIdealOutput() throws IOException{
		Calendar newFrom = getFrom();
		Calendar newTo = getTo();

		//TODO: implementar proximo dia util
		newFrom.add(Calendar.DAY_OF_MONTH, 1);
		newTo.add(Calendar.DAY_OF_MONTH, 1);
		

		System.out.println("TrainingFactory.getIdealOutput");
		System.out.println("MaxValue: " + getMaxValue());
		System.out.println("MinValue: " + getMinValue());
		System.out.println("Margin: " + getMargin());
		Data resp = new Data(getTicker(), newFrom, newTo, getMargin(), getMaxValue(), getMinValue(), getMaxNormalizedValue(), getMinNormalizedValue(), true);
		
		
		return resp;
	}
	
	
	
	//Simple getters and setters
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

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

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
	
	
	
}
