package imports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import types.Data;
import types.DataOLD;
import types.HistoricalData;
import types.Data.enumAttributesOfData;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist

public class Normalize {
	
	private final static double maxLimit = 1;
	private final static double minLimit = 0;

	private double maxValue;
	private double minValue;
	private double maxVolumeValue;
	private double minVolumeValue;
	private float margin;
	
	/*
	public HistoricalData normalizeValues (HistoricalData hd, float margin){
		getMaxAndMinValues(hd);
		
		hd.getMapHistorical().forEach(dt -> {
			
		});
		
	}
	*/
	
	private void getMaxAndMinValues(HistoricalData hd){
		ArrayList<Double> maxValues = new ArrayList<>();
		ArrayList<Double> minValues = new ArrayList<>();
		ArrayList<enumAttributesOfData> attr = hd.getMapHistorical().get(0).getAttributes();
		enumAttributesOfData atrib;
		
		for (int i=0; i<attr.size(); i++){
			//Add max from every attribute except volume
			atrib = attr.get(i);
			
			//sort by attr
			switch (atrib) {
			case closePrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getClosePrice(), d2.getClosePrice()));
				minValues.add(hd.getMapHistorical().get(0).getClosePrice());
				maxValues.add(hd.getMapHistorical().get(hd.size).getClosePrice());
				break;
			case highPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getHighPrice(), d2.getHighPrice()));
				minValues.add(hd.getMapHistorical().get(0).getHighPrice());
				maxValues.add(hd.getMapHistorical().get(hd.size).getHighPrice());
				break;
			case lowPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getLowPrice(), d2.getLowPrice()));
				minValues.add(hd.getMapHistorical().get(0).getLowPrice());
				maxValues.add(hd.getMapHistorical().get(hd.size).getLowPrice());
				break;
			case openPrice:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getOpenPrice(), d2.getOpenPrice()));
				minValues.add(hd.getMapHistorical().get(0).getOpenPrice());
				maxValues.add(hd.getMapHistorical().get(hd.size).getOpenPrice());
				break;
			case volume:
				hd.getMapHistorical().sort((Data d1, Data d2) -> Double.compare(d1.getVolume(), d2.getVolume()));
				maxVolumeValue = hd.getMapHistorical().get(0).getVolume();
				minVolumeValue = hd.getMapHistorical().get(hd.size).getVolume();
				break;
			}
		}

		maxValue = Collections.max(maxValues);
		minValue = Collections.min(minValues);
	}
	
 	public static double[][] normalizeValues (DataOLD data) throws IOException{
		double[][] values = data.getValues();
		double maxValue = data.getMaxValue();
		double minValue = data.getMinValue();
		float margin = data.getMargin();
		
		System.out.println("normalizeValues");
		System.out.println("MaxValue: " + maxValue);
		System.out.println("MinValue: " + minValue);
		System.out.println("Margin: " + margin);
		
		if (maxValue == 0) throw new IOException("Valor máximo nulo");
		
		
		for (int i=0; i<values.length; i++){
			double[] atr = values[i];
			
			for (int j=0; j< atr.length; j++){
				values[i][j] = getNormalizedValue(values[i][j], minValue, maxValue, margin);
			}
		}
		
		return values;
	}
	
	
	private static double getNormalizedValue(double oldValue, double minValue, double maxValue, float margin){
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
	
	public static double getDenomarlizedValue(double normalizedValue, double minValue, double maxValue, float margin){
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
		double vlr =  Normalize.getDenomarlizedValue(normalizedValue, getMinValue(), getMaxValue(), getMargin());
		
		//round in 2 precision
		vlr *= 100;
		vlr = Math.round(vlr);
		vlr /= 100;
		
		return vlr;
	}

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
