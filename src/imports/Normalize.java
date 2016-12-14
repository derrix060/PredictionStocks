package imports;

import java.io.IOException;

import types.Data;

//example
//https://github.com/surmenok/MNISTNeuralNetwork/tree/master/src/main/java/com/surmenok/pavel/mnist

public class Normalize {
	
	private final static double maxLimit = 1;
	private final static double minLimit = 0;
	 /*
	public static Set<Ticker> normalizeValues(Set<Ticker> tickers){
		return normalizeValues(tickers,  defaultMargin);
	}
	*/
	
	public static double[][] normalizeValues (Data data) throws IOException{
		double[][] values = data.getValues();
		double maxValue = data.getMaxValue();
		double minValue = data.getMinValue();
		float margin = data.getMargin();
		
		System.out.println("normalizeValues");
		System.out.println("MaxValue: " + maxValue);
		System.out.println("MinValue: " + minValue);
		System.out.println("Margin: " + margin);
		
		if (maxValue == 0) throw new IOException("Valor m�ximo nulo");
		
		
		for (int i=0; i<values.length; i++){
			double[] atr = values[i];
			
			for (int j=0; j< atr.length; j++){
				values[i][j] = getNormalizedValue(values[i][j], minValue, maxValue, margin);
			}
		}
		
		return values;
	}
	
	/*
	public static Set<Ticker> normalizeValues(Set<Ticker> tickers, float margin){
		tickers = normalizeOpenPrice(tickers,  margin);
		tickers = normalizeHighPrice(tickers,  margin);
		tickers = normalizeLowPrice(tickers,  margin);
		tickers = normalizeClosePrice(tickers,  margin);
		tickers = normalizeVolume(tickers,  margin);
		
		return tickers;
	}
	
	private static Set<Ticker> normalizeOpenPrice(Set<Ticker> tickers,  float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getOpenPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getOpenPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setOpenPrice(getNormalizedValue(tick.getOpenPrice(), minValue, maxValue,  margin));
		}
		
		return tickers;
	}
	
	private static Set<Ticker> normalizeHighPrice(Set<Ticker> tickers,  float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getHighPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getHighPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setHighPrice(getNormalizedValue(tick.getHighPrice(), minValue, maxValue,  margin));
		}
		
		return tickers;
	}
	
	private static Set<Ticker> normalizeLowPrice(Set<Ticker> tickers,  float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getLowPrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getLowPrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setLowPrice(getNormalizedValue(tick.getLowPrice(), minValue, maxValue,  margin));
		}
		
		return tickers;
	}
	
	private static Set<Ticker> normalizeClosePrice(Set<Ticker> tickers,  float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getClosePrice()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getClosePrice()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setClosePrice(getNormalizedValue(tick.getClosePrice(), minValue, maxValue,  margin));
		}
		
		return tickers;
	}
	
	private static Set<Ticker> normalizeVolume(Set<Ticker> tickers,  float margin){
		double minValue = tickers.stream().mapToDouble(t -> t.getVolume()).min().getAsDouble();
		double maxValue = tickers.stream().mapToDouble(t -> t.getVolume()).max().getAsDouble();
		
		for (Ticker tick : tickers){
			tick.setVolume(getNormalizedValue(tick.getVolume(), minValue, maxValue,  margin));
		}
		
		return tickers;
	}
	*/
	
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
	
	@SuppressWarnings("unused")
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

}
