/*
 * BookRepositoryTest.java
 *
 * Created on 2018-02-28, 7:07
 */
package com.marcnuri.spring.mongo.customrepository.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcnuri.spring.mongo.customrepository.EmbeddedMongoConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		BookRepository.class
})
@EnableMongoRepositories()
@Import(EmbeddedMongoConfiguration.class)
public class BookRepositoryTest {

	private static final String DEFAULT_BOOKS_JSON = "/books.json";

	private static final Logger log = LoggerFactory.getLogger(BookRepositoryTest.class);

	@Autowired
	private BookRepository bookRepository;

	private ObjectMapper objectMapper;

	@Before
	public void setUp(){
		objectMapper = new ObjectMapper();

		bookRepository.deleteAll();

		final StringBuilder jsonDocument = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(BookRepositoryTest.class.getResourceAsStream(DEFAULT_BOOKS_JSON)))) {
			String line;
			while((line = br.readLine()) != null) {
				jsonDocument.append(line);
			}
			final List<Book> books =
					objectMapper.readValue(jsonDocument.toString(), new TypeReference<List<Book>>(){});
			bookRepository.insert(books);
		} catch (IOException ex) {
			log.error("Error loading JSON", ex);
		}

	}

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Test
	public void findByTitleContainingOrderByTitle_existingTitle_shouldReturnList() {
		// Given
		final String existingBookPartialTitle = "lean Code";

		// When
		final List<Book> books = bookRepository.findByTitleContainingOrderByTitle(existingBookPartialTitle);

		// Then
		final int expectedCount = 1;
		Assert.assertEquals(expectedCount, books.size());
		Assert.assertEquals(books.size(), books.stream().filter(
				b -> b.getTitle().contains(existingBookPartialTitle)).count());
	}


}
