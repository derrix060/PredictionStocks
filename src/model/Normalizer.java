package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import model.Data.enumAttributesOfData;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist
/**
 * Values modifier to be usefull to Neural Network
 * and return to meaningful value
 * @author mario
 *
 */
public class Normalizer {
	
	private double maxLimit;
	private double minLimit;

	private double maxValue;
	private double minValue;
	private double maxVolumeValue;
	private double minVolumeValue;
	private float margin;
		
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
			for (enumAttributesOfData atr : dt.getAttributes()){
				
				if(atr.equals(enumAttributesOfData.volume))
					dt.setVolume(getNormalizedValue(dt.getVolume(),
							minVolumeValue, maxVolumeValue, margin, maxLimit, minLimit));
				
				else
					dt.setValue(atr, getNormalizedValue(dt.getValue(atr),
							minValue, maxValue, margin, maxLimit, minLimit));
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
		
		
		Stream<Data> stream = hd.getMapHistorical().parallelStream();
		
		attr.forEach(atr -> {
			DoubleSummaryStatistics datas;
			
			switch (atr) {
			case closePrice:
				datas = stream.mapToDouble(Data::getClosePrice).summaryStatistics();
				minValues.add(datas.getMin());
				maxValues.add(datas.getMax());
				break;
				
			case highPrice:
				datas = stream.mapToDouble(Data::getHighPrice).summaryStatistics();
				minValues.add(datas.getMin());
				maxValues.add(datas.getMax());
				break;
				
			case lowPrice:
				datas = stream.mapToDouble(Data::getLowPrice).summaryStatistics();
				minValues.add(datas.getMin());
				maxValues.add(datas.getMax());
				break;
				
			case openPrice:
				datas = stream.mapToDouble(Data::getOpenPrice).summaryStatistics();
				minValues.add(datas.getMin());
				maxValues.add(datas.getMax());
				break;
				
			case volume:
				datas = stream.mapToDouble(Data::getVolume).summaryStatistics();
				minVolumeValue = (datas.getMin());
				maxVolumeValue = (datas.getMax());
				break;
			}
		});

		this.maxValue = Collections.max(maxValues);
		this.minValue = Collections.min(minValues);
		
		
		//sort by date again
		hd.getMapHistorical().sort(Data::compareByDate);
	}
	
	/**
	 * Normalize value using margin.
	 * @param oldValue - value to be normalized
	 * @param minValue - minimum value for the serie
	 * @param maxValue - maximum value for the serie
	 * @param margin - margin to be considerated
	 * @param maxLimit - maximum value to be used in NN
	 * @param minLimit - minimum value to be used in NN
	 * @return - old value normalized
	 */
	private static double getNormalizedValue(double oldValue, double minValue,
			double maxValue, float margin, double maxLimit, double minLimit){
		/*
		       (X - Xmin) (D2 - D1)  
		  Y =	--------------------   +  D1
		  		    (Xmax - Xmin)
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
	
	private static double getDenomarlizedValue(double normalizedValue, double minValue,
			double maxValue, float margin, double maxLimit, double minLimit){
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
	
}
