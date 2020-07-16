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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.data.vo.v1.BookVO;
import br.com.springboot.services.BookService;
import br.com.springboot.util.CustomMediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Book Endpoint"})
@RestController
@RequestMapping("/api/book/v1")
public class BookController implements ModelController<BookVO, Integer>{
	
	@Autowired
	BookService bookService;
	
	@Autowired
	PagedResourcesAssembler<BookVO> assembler;

	@ApiOperation(value = "Find Book By ID")
	@GetMapping(value = "/{id}", produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public BookVO findById(@PathVariable("id") Integer id) {
		BookVO book = bookService.findById(id);
		book.add(buildLink(book));
		return book;
	}

	@ApiOperation(value = "Find All Books")
	@GetMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			  					@RequestParam(value = "limit", defaultValue = "15") int limit,
			  					@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		Pageable pageable = PageRequest.of(page, limit, sortDirection(direction, "author"));
		
		Page<BookVO> books = bookService.findAll(pageable);
		books.stream().forEach(p -> p.add(buildLink(p)));
		
		PagedResources<?> resources = assembler.toResource(books);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@PostMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				 consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public BookVO create(@RequestBody BookVO bookParam) {
		BookVO book = bookService.create(bookParam);
		book.add(buildLink(book));
		return book;
	}
	
	@ApiOperation(value = "Create Book")
	@PutMapping(produces = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE},
				consumes = {CustomMediaType.APPLICATION_JSON_VALUE, CustomMediaType.APPLICATION_XML_VALUE, CustomMediaType.APPLICATION_YAML_VALUE})
	public BookVO update(@RequestBody BookVO bookParam) {
		BookVO book = bookService.update(bookParam);
		book.add(buildLink(book));
		return book;
	}

	@ApiOperation(value = "Delete Book")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		bookService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public Link buildLink(BookVO book) {
		return linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel();
	}
	
}
