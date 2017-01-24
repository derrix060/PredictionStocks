package types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import imports.YahooExtractor;
import types.Data.enumAttributesOfData;

public class HistoricalData implements Comparator<Data>{
	
	public final int size;
	private int dateInterval;
	private ArrayList<Data> mapHistorical;

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval, List<enumAttributesOfData> attributes) throws IOException {
		this.dateInterval = dateInterval;
		mapHistorical = YahooExtractor.getHistorical(ticker, from, to, attributes);
		this.size = mapHistorical.size();
	}
	
	public HistoricalData (HistoricalData hd){
		this.size = hd.size;
		this.dateInterval = hd.dateInterval;
		this.mapHistorical = hd.getMapHistorical();
	}
	
	public ArrayList<Data> getMapHistorical() {
		return mapHistorical;
	}
	
	public double[][] toInput(ArrayList<enumAttributesOfData> attr){
		int elementsQty = mapHistorical.size() - dateInterval;
		int attrSize = attr.size();
		int attQty = attrSize * dateInterval;
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
	
	public double[][] toIdealOutput(ArrayList<enumAttributesOfData> attr){
		int elementsQty = mapHistorical.size() - dateInterval;
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
