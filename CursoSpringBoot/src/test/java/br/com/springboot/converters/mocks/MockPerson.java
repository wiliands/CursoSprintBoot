package br.com.springboot.converters.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.springboot.data.entity.Person;
import br.com.springboot.data.vo.v1.PersonVO;

public class MockPerson {
	
	public Person mockEntity() {
		return mockEntityList().iterator().next();
	}

	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			Person person = mockEntity(i);
			persons.add(person);
		}
		return persons;
	}

	private Person mockEntity(Integer id) {
		Person person = new Person();
		person.setId(id.longValue());
		person.setFirstName(String.format("Mock %s", id));
		person.setFirstName(String.format("LastName %s", id));
		person.setAddress(String.format("Address %s", id));
		person.setGender(String.format("Gender %s", ((id % 2 == 0) ? "F" : "M")));
		return person;
	}
	
	public PersonVO mockVO() {
		return mockVOList().iterator().next();
	}

	public List<PersonVO> mockVOList() {
		List<PersonVO> persons = new ArrayList<PersonVO>();
		for (int i = 0; i < 14; i++) {
			PersonVO person = mockVO(i);
			persons.add(person);
		}
		return persons;
	}

	private PersonVO mockVO(Integer id) {
		PersonVO person = new PersonVO();
		person.setKey(id.longValue());
		person.setFirstName(String.format("Mock VO %s", id));
		person.setFirstName(String.format("LastName VO %s", id));
		person.setAddress(String.format("Address VO %s", id));
		person.setGender(String.format("Gender VO %s", ((id % 2 == 0) ? "F" : "M")));
		return person;
	}

}
