package com.marcnuri.spring.mongo.customrepository.book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
public interface BookRepository extends MongoRepository<Book, String>, BookReposiotryCustom {

	List<Book> findByTitleContainingOrderByTitle(String titleContains);

}
