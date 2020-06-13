package br.com.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.springboot.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Wilian");
		person.setLastName("Domingues");
		person.setAddress("Rua Teste, 12");
		person.setGender("M");
		return person;
	}
	
	public List<Person> findAll() {
		
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person Name " + i);
		person.setLastName("Last Name " + i);
		person.setAddress("Address " + i);
		person.setGender("M");
		return person;
	}
	
	public Person create(Person person) {
		person.setId(counter.incrementAndGet());
		return person;
	}
	
	public Person update(Person person) {
		return person;
	}
	
	public void delete(String id) {
	}
	
}
