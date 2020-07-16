package br.com.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.springboot.converters.DozerConverter;
import br.com.springboot.data.entity.Book;
import br.com.springboot.data.vo.v1.BookVO;
import br.com.springboot.exceptions.ResourceNotFoundException;
import br.com.springboot.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO findById(Integer id) {
		var entity = repository.findById(id)
						 .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public Page<BookVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToBookVO);
	}
	
	public BookVO convertToBookVO(Book entity) {
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public BookVO create(BookVO person) {
		var entity = DozerConverter.parseObject(person, Book.class);
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, BookVO.class);
	}

	public BookVO update(BookVO book) {
		var vo = findById(book.getKey());
		
		var entity = DozerConverter.parseObject(vo, Book.class);
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		entity = repository.save(entity);
		
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public void delete(Integer id) {
		var entity = repository.findById(id)
							   .orElseThrow(() -> new ResourceNotFoundException(String.format("No records found for this ID #%s", id)));
		
		repository.delete(entity);
	}
	
}
