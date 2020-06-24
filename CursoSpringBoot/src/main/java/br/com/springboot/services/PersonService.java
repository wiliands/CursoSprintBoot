package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springboot.converters.DozerConverter;
import br.com.springboot.data.entity.Person;
import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var vo = findById(person.getKey());
		
		var entity = DozerConverter.parseObject(vo, Person.class);
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		var person = repository.findById(id)
							   .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		repository.delete(person);
	}
	
}
