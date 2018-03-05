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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

		loadDefaultBooks();

	}

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Test
	public void findByTitleContainingOrderByTitle_existingTitle_shouldReturnList() {
		// Given
		// DB with default books
		final String existingBookPartialTitle = "lean Code";

		// When
		final List<Book> books = bookRepository.findByTitleContainingOrderByTitle(existingBookPartialTitle);

		// Then
		final int expectedCount = 1;
		Assert.assertEquals(expectedCount, books.size());
		Assert.assertEquals(books.size(), books.stream().filter(
				b -> b.getTitle().contains(existingBookPartialTitle)).count());
	}

	@Test
	public void findByTitleContainingOrderByTitle_nonExistingTitle_shouldReturnEmpty() {
		// Given
		// DB with default books
		final String nonExistingBookPartialTitle = "This is the way society functions. Aren't you a part of society?";

		// When
		final List<Book> books = bookRepository.findByTitleContainingOrderByTitle(nonExistingBookPartialTitle);

		// Then
		Assert.assertTrue(books.isEmpty());
	}

	@Test
	public void query_author_shouldReturnList() {
		// Given
		// DB with default books
		final String existingAuthor = "DaViD THoMA";
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setAuthorNameLike(existingAuthor);

		// When
		final List<Book> books = bookRepository.query(dynamicQuery);

		// Then
		final int expectedCount = 1;
		Assert.assertEquals(expectedCount, books.size());
	}

	@Test
	public void query_badAuthor_shouldReturnEmptyList() {
		// Given
		// DB with default books
		final String nonExistingAuthorInjectdd = "DaV?D T*oMA";
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setAuthorNameLike(nonExistingAuthorInjectdd);

		// When
		final List<Book> books = bookRepository.query(dynamicQuery);

		// Then
		Assert.assertTrue(books.isEmpty());
	}

	@Test
	public void query_publishDateBefore_shouldReturnList() {
		// Given
		// DB with default books
		final Date ancientDate = Date.from(LocalDate.of(1985, 10, 26)
				.atStartOfDay().atZone(ZoneId.of("GMT")).toInstant());
		final DynamicQuery dynamicQuery = new DynamicQuery();
		dynamicQuery.setPublishDateBefore(ancientDate);

		// When
		final List<Book> books = bookRepository.query(dynamicQuery);

		// Then
		final int expectedCount = 1;
		Assert.assertEquals(expectedCount, books.size());
	}
	private void loadDefaultBooks() {
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
}
