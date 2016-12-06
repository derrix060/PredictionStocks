package types;

public class Datas {
	
	private double maxValue;
	private double minValue;
	private double maxNormalizedValue;
	private double minNormalizedValue;
	private double[][] values;
	private double[][] normalizedValues;
	
	public Datas() {
	}
	
	public void updateMaxValue(){
		
	}
	public void updateMinValue(){
		
	}
	public void updateMaxNormValue(){
		
	}
	public void updateMinNormValue(){
		
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
	
}
