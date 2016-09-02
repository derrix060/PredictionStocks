package process;

import java.util.ArrayList;


import types.Ticker;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist

public class Normalize {
	private final static float defaultMargin = 0.5f;
	private final static double maxLimit = 1;
	private final static double minLimit = 0;
	
	public static ArrayList<Ticker> normalizeValues(ArrayList<Ticker> tickers){
		return normalizeValues(tickers, defaultMargin);
	}
	public static ArrayList<Ticker> normalizeValues(ArrayList<Ticker> tickers, float margin){
		
		float minValue = new Float(tickers.stream().mapToDouble(t -> t.getClosePrice()).min().getAsDouble());
		float maxValue = new Float(tickers.stream().mapToDouble(t -> t.getClosePrice()).max().getAsDouble());
		
		for (Ticker tick : tickers){
			tick.setNormalizedValue(getNormalizedValue(tick, minValue, maxValue, margin));
		}
		
		return tickers;
	}
	
	private static double getNormalizedValue(Ticker t, float minValue, float maxValue, float margin){
		//extracted from: https://www.mql5.com/pt/articles/497
		
		double adjustedMaxLimit = maxLimit - margin;
		double adjustedMinLimit = margin + minLimit;
		
		double norm;
		norm = t.getClosePrice() - minValue;
		norm *= (adjustedMaxLimit - adjustedMinLimit);
		norm /= (maxValue - minValue);
		norm += adjustedMinLimit;
		return norm;
	}
}
