package br.com.springboot.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.data.vo.v1.PersonVO;
import br.com.springboot.services.PersonService;
import br.com.springboot.util.CustomMediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Person Endpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController implements ModelController<PersonVO, Long>{
	
	@Autowired
	PersonService personService;
	
	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;

	@ApiOperation(value = "Find Person By ID")
	@GetMapping(value = "/{id}", produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = personService.findById(id);
		personVO.add(buildLink(personVO));
		return personVO;
	}

	@ApiOperation(value = "Find All Persons")
	@GetMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
								  @RequestParam(value = "limit", defaultValue = "15") int limit,
								  @RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = PageRequest.of(page, limit, sortDirection(direction, "firstName"));
		
		Page<PersonVO> persons = personService.findAll(pageable);
		persons.stream().forEach(p -> p.add(buildLink(p)));

		PagedResources<?> resources = assembler.toResource(persons);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	

	@ApiOperation(value = "Find All Persons By Name")
	@GetMapping(value = "/findByFirstName", produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<?> findByFirstName(@RequestBody PersonVO person,
								  @RequestParam(value = "page", defaultValue = "0") int page,
								  @RequestParam(value = "limit", defaultValue = "15") int limit,
								  @RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		Pageable pageable = PageRequest.of(page, limit, sortDirection(direction, "firstName"));
		
		Page<PersonVO> persons = personService.findByFirstName(person.getFirstName(), pageable);
		persons.stream().forEach(p -> p.add(buildLink(p)));
		
		PagedResources<?> resources = assembler.toResource(persons);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create Person")
	@PostMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				 consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = personService.create(person);
		personVO.add(buildLink(personVO));
		return personVO;
	}
	
	@ApiOperation(value = "Update Person")
	@PutMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = personService.update(person);
		personVO.add(buildLink(personVO));
		return personVO;
	}

	@ApiOperation(value = "Delete Person")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public Link buildLink(PersonVO person) {
		return linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel();
	}
	
	@ApiOperation(value = "Disable Person")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> disable(@PathVariable("id") Long id) {
		personService.disable(id);
		return ResponseEntity.ok().build();
	}
	
}
