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
	
	public HistoricalData(int size){
		this.size = size;
		
	}

	public HistoricalData(String ticker, Calendar from, Calendar to, int dateInterval, List<enumAttributesOfData> attributes) throws IOException {
		this.dateInterval = dateInterval;
		mapHistorical = YahooExtractor.getHistorical(ticker, from, to, attributes);
		this.size = mapHistorical.size();
	}
		
	public HistoricalData createTrainHistoricalData (ArrayList<enumAttributesOfData> attr, Calendar to){
		ArrayList<Data> datas = new ArrayList<>();
		Calendar actualDate = mapHistorical.get(0).getDate();
		int i = 0;
		Data oldData = new Data();
		
		while (actualDate.before(to) && i < mapHistorical.size()){
			actualDate = mapHistorical.get(i).getDate();
			//for each date
			
			Data dt = new Data();
			dt.setTicker(mapHistorical.get(0).getTicker());
			dt.setDate(actualDate);
			oldData = mapHistorical.get(i);
			
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
	
	/**
	 * Getter and setters
	 */
	public int getDateInterval(){
		return this.dateInterval;
	}
	
	public ArrayList<Data> getMapHistorical() {
		return mapHistorical;
	}
	
	public void setMapHistorical(ArrayList<Data> hist){
		this.mapHistorical = hist;
	}
}
