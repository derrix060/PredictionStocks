package types;

public class Input {
	
	private double maxValue;
	private double minValue;
	private double maxNormalizedValue;
	private double minNormalizedValue;
	private double[][] values;
	
	public Input() {
		
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
	public double[][] getValues() {
		return values;
	}

	public void setValues(double[][] values) {
		this.values = values;
	}
	
}
