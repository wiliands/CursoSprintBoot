package br.com.springboot.controllers;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import br.com.springboot.data.vo.v1.ModelVO;

public interface ModelController<T extends ModelVO<ID>, ID> {
	
	public T findById(ID id);

	public List<T> findAll();
	
	public T create(T person);
	
	public T update(T person);

	public ResponseEntity<?> delete(ID id);
	
	/**
	 * Construir link HATEOAS
	 * @param personVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default Link buildLink(T t) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).findById(t.getKey())).withSelfRel();
	}

}
