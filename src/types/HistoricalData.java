package types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import imports.YahooExtractor;
import types.Data.enumAttributesOfData;

public class HistoricalData {
	
	private int dateInterval;
	private ArrayList<Data> mapHistorical;

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval) throws IOException {
		this.dateInterval = dateInterval;
		mapHistorical = YahooExtractor.getHistorical(ticker, from, to);
	}
	
	public ArrayList<Data> getMapHistorical() {
		return mapHistorical;
	}
	
	public double[][] toInput(){
		int elementsQty = mapHistorical.size() - dateInterval;
		ArrayList<enumAttributesOfData> attr = mapHistorical.get(0).getAttributes();
		int attrSize = attr.size();
		int attQty = attrSize * dateInterval;
		System.out.println("Teste attSize: " + attQty);
		double[][] rtn = new double[elementsQty][attQty];
		
		for (int i=0; i<elementsQty; i++){
			//for each element (row)
			
			for (int j=0; j<dateInterval; j ++){
				//for each date
				
				for (int k=0; k<attrSize; k++){
					//for each day
					rtn[i][(j * attrSize) + k] = mapHistorical.get(i+j).getValue(attr.get(k));
				}
			}
		}
		
		
		return rtn;
		
	}

}
