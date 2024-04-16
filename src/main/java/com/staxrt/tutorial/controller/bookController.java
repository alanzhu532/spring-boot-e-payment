
package com.staxrt.tutorial.controller; 

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.staxrt.tutorial.model.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.repository.bookRepository;


//@CrossOrigin(origins = {"http://localhost:8080", "http://d5530p:8080"}, allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header", maxAge = 3600 ) 
//@CrossOrigin(origins = {"http://localhost:8080", "http://d5530p:8080"}, maxAge = 3600)   
/**
 * @author alanz 
 */
@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/api/v1")
public class bookController {
	@Autowired
	private bookRepository bookRepository;

	@GetMapping("/books")
	public List<book> getAllbooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<book> getbookById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("book not found for this id : " + id));
		return ResponseEntity.ok().body(book);
	}

	@PostMapping("/books")
	public book createbook(@Valid @RequestBody book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<book> updatebook(@PathVariable(value = "id") Long id,
			@Valid @RequestBody book bookDetails) throws ResourceNotFoundException, ParseException {
		book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("book not found for this id : " + id));

		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setPublishdate(bookDetails.getPublishdate());
		
		final book updatedbook = bookRepository.save(book);
		return ResponseEntity.ok(updatedbook);
	}

	@DeleteMapping("/books/{id}")
	public void deletebook(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("book not found for this id : " + id));
		bookRepository.delete(book);
	}
	//public Map<String, Boolean> deletebook(@PathVariable(value = "id") Long id)
	//		throws ResourceNotFoundException {
	//	book book = bookRepository.findById(id)
	//			.orElseThrow(() -> new ResourceNotFoundException("book not found for this id :: " + id));
	//
	//	bookRepository.delete(book);
	//	
	//	Map<String, Boolean> response = new HashMap<>();
	//	response.put("deleted", Boolean.TRUE);
	//	return response;
	//}
	
}

