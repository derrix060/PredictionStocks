package types;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class Data {
	private Calendar date;
	private String ticker;
	private double openPrice;
	private double highPrice;
	private double lowPrice;
	private double closePrice;
	private double volume;
	private List<enumAttributesOfData> attributes;
	
	public enum enumAttributesOfData{
		openPrice,
		highPrice,
		lowPrice,
		closePrice,
		volume;
	}
	
	//constructor
	public Data(){
		attributes = new ArrayList<>();
	}
	
	@Override
	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return "Ticker: " + getTicker() + " Date: " + format.format(getDate().getTime())+ " openPrice: " + getOpenPrice() + " highPrice: " + getHighPrice() + " lowPrice: " + getLowPrice() + " closePrice: " + getClosePrice() + " Volume: " + getVolume();
	}
	
	private void addToAttributes(enumAttributesOfData atr){
		if (!attributes.contains(atr)){
			attributes.add(atr);
		}
	}

	//getters and setters
	public double getValue (enumAttributesOfData atr){
		switch (atr) {
			case closePrice:
				return getClosePrice();		
			case highPrice:
				return getHighPrice();
			case lowPrice:
				return getLowPrice();
			case openPrice:
				return getOpenPrice();
			case volume:
				return getVolume();
		}
		return 0;
	}
	public void setValue (enumAttributesOfData atr, double value){
		switch (atr) {
			case closePrice:
				setClosePrice(value);		
			case highPrice:
				setHighPrice(value);
			case lowPrice:
				setLowPrice(value);
			case openPrice:
				setOpenPrice(value);
			case volume:
				setVolume(value);
		}
	}
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
		addToAttributes(enumAttributesOfData.closePrice);
		this.closePrice = d;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		addToAttributes(enumAttributesOfData.openPrice);
		this.openPrice = openPrice;
	}
	public double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(double highPrice) {
		addToAttributes(enumAttributesOfData.highPrice);
		this.highPrice = highPrice;
	}
	public double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(double lowPrice) {
		addToAttributes(enumAttributesOfData.lowPrice);
		this.lowPrice = lowPrice;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		addToAttributes(enumAttributesOfData.volume);
		this.volume = volume;
	}

	public ArrayList<enumAttributesOfData> getAttributes() {
		return (ArrayList<enumAttributesOfData>) attributes;
	}

	public void setAttributes(ArrayList<enumAttributesOfData> attributes) {
		this.attributes = attributes;
	}
	
}
