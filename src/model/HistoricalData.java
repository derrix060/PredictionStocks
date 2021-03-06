package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Data.enumAttributesOfData;

public class HistoricalData{
	
	public int size;
	private int dateInterval;
	private ArrayList<Data> historicalValues;
	
	public HistoricalData(){
		this.size = 0;
	}
	

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval, List<enumAttributesOfData> attributes) throws IOException {
		this.dateInterval = dateInterval;
		historicalValues = DataExtractor.getHistorical(ticker, from, to, attributes);
		this.size = historicalValues.size();
	}
	
	public double[][] toInput(ArrayList<enumAttributesOfData> attr){
		int elementsQty = historicalValues.size() - dateInterval;
		int attrSize = attr.size();
		int attQty = attrSize * dateInterval;
		double[][] rtn = new double[elementsQty][attQty];
		
		for (int e=0; e<elementsQty; e++){
			//for each element (row)
			
			for (int d=0; d<dateInterval; d ++){
				//for each date
				
				for (int a=0; a<attrSize; a++){
					//for each attribute
					rtn[e][(d * attrSize) + a] = historicalValues.get(e+d).getValue(attr.get(a));
				}
			}
		}
		
		
		return rtn;
		
	}
	
	public double[][] toIdealOutput(ArrayList<enumAttributesOfData> attr){
		int elementsQty = historicalValues.size() - dateInterval;
		int attrSize = attr.size();
		double[][] rtn = new double[elementsQty][attrSize];
		
		for (int i=0; i<elementsQty; i++){
			//for each element (row)
			
			for (int j=0; j<attrSize; j++){
				//for each attribute
				rtn[i][j] = historicalValues.get(dateInterval + i).getValue(attr.get(j));
			}
		}
		
		return rtn;
	}


	/**
	 * Getter and setters
	 */
	public int getDateInterval(){
		return this.dateInterval;
	}
	
	public ArrayList<Data> getMapHistorical() {
		return historicalValues;
	}
	
	public void setMapHistorical(ArrayList<Data> hist){
		this.historicalValues = hist;
		this.size = hist.size();
	}
	
	public void setDateInterval(int interval){
		this.dateInterval = interval;
	}
}
