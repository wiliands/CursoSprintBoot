package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.model.Person;
import br.com.springboot.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;

	public Person findById(Long id) {
		return repository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person create(Person person) {
		return repository.save(person);
	}
	
	public Person update(Person person) {
		Person entity = findById(person.getId());
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		Person person = findById(id);
		repository.delete(person);
	}
	
}
