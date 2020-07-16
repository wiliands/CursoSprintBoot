package br.com.springboot.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;

import br.com.springboot.data.vo.v1.ModelVO;

public interface ModelController<T extends ModelVO<ID>, ID> {
	
	public T findById(ID id);

	public ResponseEntity<?> findAll(int page, int limit, String direction);
	
	public T create(T t);
	
	public T update(T t);

	public ResponseEntity<?> delete(ID id);
	
	public Link buildLink(T t);
	
	default Sort sortDirection(String direction, String fieldName) {
		var sortDirection = StringUtils.isBlank(direction) || "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		return Sort.by(sortDirection, fieldName);
	}

}
