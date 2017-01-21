package types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import imports.YahooExtractor;
import types.Data.enumAttributesOfData;

public class HistoricalData implements Comparator<Data>{
	
	public final int size;
	private int dateInterval;
	private ArrayList<Data> mapHistorical;

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval) throws IOException {
		this.dateInterval = dateInterval;
		mapHistorical = YahooExtractor.getHistorical(ticker, from, to);
		this.size = mapHistorical.size();
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
					//for each attribute
					rtn[i][(j * attrSize) + k] = mapHistorical.get(i+j).getValue(attr.get(k));
				}
			}
		}
		
		
		return rtn;
		
	}
	
	public double[][] toIdealOutput(){
		int elementsQty = mapHistorical.size() - dateInterval;
		ArrayList<enumAttributesOfData> attr = mapHistorical.get(0).getAttributes();
		int attrSize = attr.size();
		double[][] rtn = new double[elementsQty][attrSize];
		
		for (int i=0; i<elementsQty; i++){
			//for each element (row)
			
			for (int j=0; j<attrSize; j++){
				//for each attribute
				rtn[i][j] = mapHistorical.get(dateInterval + i).getValue(attr.get(j));
			}
		}
		
		return rtn;
	}

	@Override
	public int compare(Data o1, Data o2) {
		return o1.getDate().compareTo(o2.getDate());
	}


}
