package types;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HistoricalData {
	
	private Map<Calendar, Data> datas = new HashMap<>();

	public HistoricalData() {
		// TODO Auto-generated constructor stub
	}

	public Map<Calendar, Data> getDatas() {
		return datas;
	}

}
