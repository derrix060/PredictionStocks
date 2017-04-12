






package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.OptionalDouble;

import model.Data;
import model.Data.enumAttributesOfData;
import model.HistoricalData;

	@SuppressWarnings("unused")	
	public class _oldTeste {
		
		public static void main (String args[]) throws IOException{
			
			exemploParallel();			
		}
		
		
		
		public static void exemploStream1() throws IOException{
			String ticker = "PETR4.SA";
			Calendar from = Calendar.getInstance();
			from.set(2017, Calendar.JANUARY, 12);
			Calendar to = Calendar.getInstance();
			to.set(2017, Calendar.MARCH, 24);
			int dateInterval = 1;
			ArrayList<enumAttributesOfData> attr = new ArrayList<>();
			attr.add(enumAttributesOfData.closePrice);
			attr.add(enumAttributesOfData.highPrice);
			attr.add(enumAttributesOfData.volume);
			
			HistoricalData historical = new HistoricalData(ticker,
					from, to, dateInterval, attr);
			
			ArrayList<Data> historicalDatas = historical.getMapHistorical();
			
			//historicalDatas.forEach(System.out::println);
			
			Double media = historicalDatas
				.parallelStream()
				.filter(dt -> dt.getVolume() > 60000000.0)
				.mapToDouble(Data::getClosePrice)
				.average()
				.getAsDouble();
			
			System.out.println("\n\nMedia: " + media);
		}
		
		public static void exemploParallel(){
			ArrayList<Integer> numeros = new ArrayList<>();
			
			for (int i=1; i<=10; i++){
				numeros.add(i);
			}
			
			System.out.println("\nLista com stream sequencial:");
			numeros
				.stream()
				.forEach(num -> System.out.print(num + " "));

			System.out.println("\n\nLista com parallelStream:");
			numeros
				.parallelStream()
				.forEach(num -> System.out.print(num + " "));

			System.out.println("\n\nMesma lista com outro parallelStream:");
			numeros
				.parallelStream()
				.forEach(num -> System.out.print(num + " "));
			
		}
		
	}
	
	
	


