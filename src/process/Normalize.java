package process;

import java.util.ArrayList;

import types.Ticker;

public class Normalize {
	private final static float defaultMargin = 0.5f;
	
	public static ArrayList<Ticker> normalizeValues(ArrayList<Ticker> tickers){
		return normalizeValues(tickers, defaultMargin);
	}
	public static ArrayList<Ticker> normalizeValues(ArrayList<Ticker> tickers, float margin){
		float minValue = minimumValue(tickers);
		float maxValue = maximumValue(tickers);
		
		for (Ticker tick : tickers){
			tick.setNormalizedValue(getNormalizedValue(tick, minValue, maxValue, margin));
		}
		
		return tickers;
	}
	
	private static double getNormalizedValue(Ticker t, float minValue, float maxValue, float margin){
		//extracted from: https://www.mql5.com/pt/articles/497
		
		double norm;
		margin /= 2;
		norm = t.getClosePrice() - minValue;
		norm *= (1 - (2*margin));
		norm /= (maxValue - minValue);
		norm += margin;
		return norm;
	}
	
	private static float maximumValue(ArrayList<Ticker> tickers){
		float ret = Float.MIN_NORMAL;
		
		for(Ticker ticker : tickers){
			if (ticker.getClosePrice() > ret)
				ret = ticker.getClosePrice();
		}
		
		return ret;
	}
	
	private static float minimumValue(ArrayList<Ticker> tickers){
		float ret = Float.MAX_VALUE;
		
		for(Ticker ticker : tickers){
			if (ticker.getClosePrice() < ret)
				ret = ticker.getClosePrice();
		}
		
		return ret;
	}
}
