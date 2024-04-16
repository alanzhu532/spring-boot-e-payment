package com.staxrt.tutorial;

import com.staxrt.tutorial.model.book;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.text.ParseException;
import java.time.LocalDateTime;    // import the LocalDateTime class 


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllbooks() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/books",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetbookById() {
		book book = restTemplate.getForObject(getRootUrl() + "/book/1", book.class);
		System.out.println(book.getTitle());
		Assert.assertNotNull(book);
	}

	@Test
	public void testCreatebook() throws ParseException {
		book book = new book();
		book.setTitle("J2EE step by step");
		book.setAuthor("Alan");
		
		Date NowDT = new Date(); 
		book.setPublishdate(NowDT);

		ResponseEntity<book> postResponse = restTemplate.postForEntity(getRootUrl() + "/books", book, book.class);
		Assert.assertNotNull(postResponse);
		
	}

	@Test
	public void testUpdatePost() {
		int id = 1;
		book book = restTemplate.getForObject(getRootUrl() + "/books/" + id, book.class);
		
		book.setAuthor("admin1");
		
		restTemplate.put(getRootUrl() + "/books/" + id, book);

		book updatedbook = restTemplate.getForObject(getRootUrl() + "/books/" + id, book.class);
		Assert.assertNotNull(updatedbook);
	}

	@Test
	public void testDeletePost() {
		int id = 2;
		book book = restTemplate.getForObject(getRootUrl() + "/books/" + id, book.class);
		Assert.assertNotNull(book);

		restTemplate.delete(getRootUrl() + "/books/" + id);

		try {
			book = restTemplate.getForObject(getRootUrl() + "/books/" + id, book.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
   
