






package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import model.Data.enumAttributesOfData;
import model.HistoricalData;

	@SuppressWarnings("unused")	
	public class _oldTeste {
		
		public static void main (String args[]) throws IOException{
			
			exemploStream1();
			
			/*
			ArrayList<Person> persons = new ArrayList<>();
			Person person = new Person();
	
			person.name = "a";
			persons.add(person);
			person.name = "b";
			persons.add(person);
			
			System.out.println(persons);
			
			
			List<Person> santos = new ArrayList<>();

			Person santo = new Person();
			santo.name = "Maria";
			santos.add(santo);
			santo = new Person();
			santo.name = "Pedro";
			santos.add(santo);
			santo = new Person();
			santo.name = "Paulo";
			santos.add(santo);
			
			
			santos.forEach((sant) -> {
				sant.printName();
			});
			
			santos.forEach(Person::printName);
			*/
			
			
			
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
			
			HistoricalData historical = new HistoricalData(ticker, from, to, dateInterval, attr);
			
			historical.getMapHistorical().forEach(System.out::println);
		}
		
	}
	
	class Person{
		String name;
		
		Person(){}
		
		public void printName(){
			System.out.println(name);
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
	
	class Carrinho{}
	
	class Item{}
	
	
	
	
	
	
	


