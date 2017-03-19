package model;

import java.util.ArrayList;
import java.util.Collections;

import model.Data.enumAttributesOfData;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist

public class Normalizer {
	
	private static final double DEFAUT_MAX_LIMIT = 1;
	private static final double DEFAUT_MIN_LIMIT = 0;
	
	private double maxLimit;
	private double minLimit;

	private double maxValue;
	private double minValue;
	private double maxVolumeValue;
	private double minVolumeValue;
	private float margin;
	
	public Normalizer (float margin){
		this(margin, DEFAUT_MAX_LIMIT, DEFAUT_MIN_LIMIT);
	}
	
	public Normalizer (float margin, double maxLimit, double minLimit){
		this.margin = margin;
		this.maxLimit = maxLimit;
		this.minLimit = minLimit;
	}
	
	/**
	 * Normalize values from Historical Data.
	 * 
	 * Warning: the Historical Data will be modified!
	 * @param hd - Historical Data
	 */
	public void normalizeDatas (HistoricalData hd){
		updateMaxAndMinValues(hd);
		
		hd.getMapHistorical().forEach(dt -> {
			for (int i=0; i<dt.getAttributes().size(); i++){
				enumAttributesOfData atr = dt.getAttributes().get(i);
				if(atr.equals(enumAttributesOfData.volume))
					dt.setVolume(getNormalizedValue(dt.getVolume(), minVolumeValue, maxVolumeValue, margin, maxLimit, minLimit));
				else
					dt.setValue(atr, getNormalizedValue(dt.getValue(atr), minValue, maxValue, margin, maxLimit, minLimit));
			}
		});
		
	}
	
	public void denormalizeDatas (HistoricalData hd){
		//mustn't getMaxAndMinValues(hd);
		
		hd.getMapHistorical().forEach(dt -> {
			for (int i=0; i<dt.getAttributes().size(); i++){
				enumAttributesOfData atr = dt.getAttributes().get(i);
				if(atr.equals(enumAttributesOfData.volume))
					dt.setVolume(getDenomarlizedValue(dt.getVolume(), minVolumeValue, maxVolumeValue, margin, maxLimit, minLimit));
				else
					dt.setValue(atr, getDenomarlizedValue(dt.getValue(atr), minValue, maxValue, margin, maxLimit, minLimit));
			}
		});
		
	}
	
	private void updateMaxAndMinValues(HistoricalData hd){
		ArrayList<Double> maxValues = new ArrayList<>();
		ArrayList<Double> minValues = new ArrayList<>();
		ArrayList<enumAttributesOfData> attr = hd.getMapHistorical().get(0).getAttributes();
		int lastItem = hd.size - 1;
		
		for (int i=0; i<attr.size(); i++){
			//sort by attr
			switch (attr.get(i)) {
			case closePrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getClosePrice(), d2.getClosePrice()));
				minValues.add(hd.getMapHistorical().get(0).getClosePrice());
				maxValues.add(hd.getMapHistorical().get(lastItem).getClosePrice());
				break;
			case highPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getHighPrice(), d2.getHighPrice()));
				minValues.add(hd.getMapHistorical().get(0).getHighPrice());
				maxValues.add(hd.getMapHistorical().get(lastItem).getHighPrice());
				break;
			case lowPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getLowPrice(), d2.getLowPrice()));
				minValues.add(hd.getMapHistorical().get(0).getLowPrice());
				maxValues.add(hd.getMapHistorical().get(lastItem).getLowPrice());
				break;
			case openPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getOpenPrice(), d2.getOpenPrice()));
				minValues.add(hd.getMapHistorical().get(0).getOpenPrice());
				maxValues.add(hd.getMapHistorical().get(lastItem).getOpenPrice());
				break;
			case volume:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getVolume(), d2.getVolume()));
				minVolumeValue = hd.getMapHistorical().get(0).getVolume();
				maxVolumeValue = hd.getMapHistorical().get(lastItem).getVolume();
				break;
			}
		}

		this.maxValue = Collections.max(maxValues);
		this.minValue = Collections.min(minValues);
		
		
		//sort by date again
		hd.getMapHistorical().sort(hd);
	}
	
	
	private static double getNormalizedValue(double oldValue, double minValue, double maxValue, float margin, double maxLimit, double minLimit){
		/*
		 *      (X - Xmin) (D2 - D1)  
		 * Y =	--------------------   +  D1
		 * 		    (Xmax - Xmin)
		 */
		
		//Adjuste margin when it's 100 %
		margin = margin == 1f?1.001f:margin;
		
		double adjustedMaxValue = maxValue * (1 + margin);
		double adjustedMinValue = minValue * (1 - margin);
		
		double norm;
		norm = oldValue - adjustedMinValue;
		norm *= (maxLimit - minLimit);
		norm /= (adjustedMaxValue - adjustedMinValue);
		norm += minLimit;
		return norm;
	}
	
	public static double getDenomarlizedValue(double normalizedValue, double minValue, double maxValue, float margin, double maxLimit, double minLimit){
		/*
		 *       Y (Xmax - Xmin) - D1
		 * X =  --------------------   +  Xmin
		 * 			(D2 - D1)
		 */
		

		//Adjuste margin when it's 100 %
		margin = margin == 1f?1.001f:margin;
		
		double adjustedMaxValue = maxValue * (1 + margin);
		double adjustedMinValue = minValue * (1 - margin);
		
		double denorm;
		denorm = normalizedValue * (adjustedMaxValue - adjustedMinValue);
		denorm -= minLimit;
		denorm /= (maxLimit - minLimit);
		denorm += adjustedMinValue;
		return denorm;
	}
	
	//not static
	public double getDenormalizedValueFrom (double normalizedValue){
		double vlr =  Normalizer.getDenomarlizedValue(normalizedValue, getMinValue(), getMaxValue(), getMargin(), maxLimit, minLimit);
		
		//round in 2 precision
		vlr *= 100;
		vlr = Math.round(vlr);
		vlr /= 100;
		
		return vlr;
	}

	
	
	// Getters and setters
	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

}
