package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import model.Data.enumAttributesOfData;

public class HistoricalData implements Comparator<Data>{
	
	public final int size;
	private int dateInterval;
	private ArrayList<Data> historicalValues;
	
	public HistoricalData(int size){
		this.size = size;
		
	}

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval, List<enumAttributesOfData> attributes) throws IOException {
		this.dateInterval = dateInterval;
		historicalValues = YahooExtractor.getHistorical(ticker, from, to, attributes);
		this.size = historicalValues.size();
	}
	
	public HistoricalData createTrainHistoricalData (ArrayList<enumAttributesOfData>attr, Calendar from, Calendar to){
		ArrayList<Data> datas = new ArrayList<>();
		Calendar actualDate = from;
		int i = 0;
		Data tempData = new Data();
		
		//while (actualDate.before(to) && i <)
		
		//TODO: change this!
		return null;
	}
	
	public HistoricalData createTrainHistoricalData (ArrayList<enumAttributesOfData> attr, Calendar to){
		ArrayList<Data> datas = new ArrayList<>();
		Calendar actualDate = historicalValues.get(0).getDate();
		int i = 0;
		Data oldData = new Data();
		
		while (actualDate.before(to) && i < historicalValues.size()){
			actualDate = historicalValues.get(i).getDate();
			//for each date
			
			Data dt = new Data();
			dt.setTicker(historicalValues.get(0).getTicker());
			dt.setDate(actualDate);
			oldData = historicalValues.get(i);
			
			for (enumAttributesOfData atr : attr){
				//for each attribute
				dt.setValue(atr, oldData.getValue(atr));
			}
			
			datas.add(dt);
			
			i ++;
		}
		
		HistoricalData rtn = new HistoricalData(datas.size());
		rtn.dateInterval = this.dateInterval;
		rtn.setMapHistorical(datas);
		
		return rtn;
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

	@Override
	public int compare(Data o1, Data o2) {
		return o1.getDate().compareTo(o2.getDate());
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
	}
}
