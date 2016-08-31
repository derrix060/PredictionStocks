package types;

import java.util.Calendar;

public class Ticker {
	private Calendar date;
	private String ticker;
	private float closePrice;
	private double normalizedValue;
	
	
	//constructor
	public Ticker(){
	}

	

	
	
	public String toString(){
		return "Ticker: " + getTicker() + " Date: " + getDate() + " closePrice: " + getClosePrice() + " normPrice: " + normalizedValue;
	}

	//getters and setters
	public void setNormalizedValue (double normalizedValue){
		this.normalizedValue = normalizedValue;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	public double getNormalizedValue() {
		return normalizedValue;
	}
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}
	
	
}
