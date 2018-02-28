/*
 * BookRepositoryTest.java
 *
 * Created on 2018-02-28, 7:07
 */
package com.marcnuri.spring.mongo.customrepository.book;

import com.marcnuri.spring.mongo.customrepository.EmbeddedMongoConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	@Autowired
	private BookRepository bookRepository;

	@Before
	public void setUp(){
		bookRepository.deleteAll();
	}

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Test
	public void findByTitleContainingOrderByTitle_existingTitle_shouldReturnList() {
		bookRepository.findByTitleContainingOrderByTitle("test");
	}


}
