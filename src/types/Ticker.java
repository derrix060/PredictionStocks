package types;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticker {
	private Calendar date;
	private String ticker;
	private double openPrice;
	private double highPrice;
	private double lowPrice;
	private double closePrice;
	private double volume;
	
	
	//constructor
	public Ticker(){
	}

	

	
	
	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return "Ticker: " + getTicker() + " Date: " + format.format(getDate().getTime())+ " openPrice: " + getOpenPrice() + " highPrice: " + getHighPrice() + " lowPrice: " + getLowPrice() + " closePrice: " + getClosePrice() + " Volume: " + getVolume();
	}

	//getters and setters
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double d) {
		this.closePrice = d;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	
}
