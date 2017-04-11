






package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

	@SuppressWarnings("unused")	
	public class _oldTeste {
		
		public static void main (String args[]){
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
	
	
	
	
	
	
	


