package br.com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.services.PersonService;
import br.com.springboot.util.CustomMediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController implements ModelController<PersonVO, Long>{
	
	@Autowired
	PersonService personService;

	@GetMapping(value = "/{id}", produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = personService.findById(id);
		personVO.add(buildLink(personVO));
		return personVO;
	}

	@GetMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public List<PersonVO> findAll() {
		List<PersonVO> persons = personService.findAll();
		persons.stream().forEach(p -> p.add(buildLink(p)));
		return persons;
	}
	
	@PostMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				 consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = personService.create(person);
		personVO.add(buildLink(personVO));
		return personVO;
	}
	
	@PutMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = personService.update(person);
		personVO.add(buildLink(personVO));
		return personVO;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
