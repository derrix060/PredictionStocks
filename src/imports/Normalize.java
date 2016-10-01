package imports;

import java.util.ArrayList;
import java.util.HashSet;

import types.Ticker;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist

public class Normalize {
	private final static float defaultMargin = 0.5f;
	private final static double maxLimit = 1;
	private final static double minLimit = 0;
	
	public static HashSet<Ticker> normalizeValues(HashSet<Ticker> tickers){
		return normalizeValues(tickers, defaultMargin);
	}
	public static HashSet<Ticker> normalizeValues(HashSet<Ticker> tickers, float margin){
		tickers = normalizeOpenPrice(tickers, margin);
		tickers = normalizeHighPrice(tickers, margin);
		tickers = normalizeLowPrice(tickers, margin);
		tickers = normalizeClosePrice(tickers, margin);
		tickers = normalizeVolume(tickers, margin);
		
		return tickers;
	}
	private static HashSet<Ticker> normalizeOpenPrice(HashSet<Ticker> tickers, float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getOpenPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getOpenPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setOpenPrice(getNormalizedValue(tick.getOpenPrice(), minValue, maxValue, margin));
		}
		
		return tickers;
	}
	private static HashSet<Ticker> normalizeHighPrice(HashSet<Ticker> tickers, float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getHighPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getHighPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setHighPrice(getNormalizedValue(tick.getHighPrice(), minValue, maxValue, margin));
		}
		
		return tickers;
	}
	private static HashSet<Ticker> normalizeLowPrice(HashSet<Ticker> tickers, float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getLowPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getLowPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setLowPrice(getNormalizedValue(tick.getLowPrice(), minValue, maxValue, margin));
		}
		
		return tickers;
	}
	private static HashSet<Ticker> normalizeClosePrice(HashSet<Ticker> tickers, float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getClosePrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getClosePrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setClosePrice(getNormalizedValue(tick.getClosePrice(), minValue, maxValue, margin));
		}
		
		return tickers;
	}
	private static HashSet<Ticker> normalizeVolume(HashSet<Ticker> tickers, float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getVolume()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getVolume()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setVolume(getNormalizedValue(tick.getVolume(), minValue, maxValue, margin));
		}
		
		return tickers;
	}
	
	private static double getNormalizedValue(double oldValue, double minValue, double maxValue, float margin){
		//extracted from: https://www.mql5.com/pt/articles/497
		
		double adjustedMaxLimit = maxLimit - margin;
		double adjustedMinLimit = margin + minLimit;
		
		double norm;
		norm = oldValue - minValue;
		norm *= (adjustedMaxLimit - adjustedMinLimit);
		norm /= (maxValue - minValue);
		norm += adjustedMinLimit;
		return norm;
	}
}