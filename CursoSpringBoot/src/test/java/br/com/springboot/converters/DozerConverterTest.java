package br.com.springboot.converters;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.springboot.converters.mocks.MockPerson;
import br.com.springboot.data.entity.Person;
import br.com.springboot.data.vo.v1.PersonVO;

public class DozerConverterTest {
	
	private MockPerson mockPerson;
	
	@Before
	public void setUp() {
		mockPerson = new MockPerson();
	}
	
	@Test
	public void parseEntityToVo() {
		PersonVO vo = DozerConverter.parseObject(mockPerson.mockEntity(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0l), vo.getKey());
	}
	
	@Test
	public void parseVoToEntity() {
		Person entity = DozerConverter.parseObject(mockPerson.mockVO(), Person.class);
		Assert.assertEquals(Long.valueOf(0l), entity.getId());
	}
	
	@Test
	public void parseEntityToVoList() {
		List<PersonVO> vos = DozerConverter.parseListObjects(mockPerson.mockEntityList(), PersonVO.class);
		Assert.assertEquals(Long.valueOf(0l), vos.get(0).getKey());
	}
	
	@Test
	public void parseVoToEntityList() {
		List<Person> entitys = DozerConverter.parseListObjects(mockPerson.mockVOList(), Person.class);
		Assert.assertEquals(Long.valueOf(0l), entitys.get(0).getId());
	}

}
