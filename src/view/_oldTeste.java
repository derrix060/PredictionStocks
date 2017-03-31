






package view;
	
	import java.util.ArrayList;
	
	public class _oldTeste {
		
		public static void main (String args[]){
			ArrayList<Person> persons = new ArrayList<>();
			Person person = new Person();
	
			person.name = "a";
			persons.add(person);
			person.name = "b";
			persons.add(person);
			
			System.out.println(persons);
		}
	}
	
	class Person{
		String name;
		
		Person(){}
		
		@Override
		public String toString(){
			return name;
		}
	}
	


