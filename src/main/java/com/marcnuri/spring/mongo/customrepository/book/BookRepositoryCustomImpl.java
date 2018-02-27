/*
 * BookRepositoryCustomImpl.java
 *
 * Created on 2018-02-27, 7:30
 */
package com.marcnuri.spring.mongo.customrepository.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
public class BookRepositoryCustomImpl implements BookReposiotryCustom {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	private final MongoTemplate mongoTemplate;

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Autowired
	public BookRepositoryCustomImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************

//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************
	@Override
	public List<Book> query(DynamicQuery dynamicQuery) {
		return null;
	}

//**************************************************************************************************
//  Other Methods
//**************************************************************************************************

//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************

//**************************************************************************************************
//  Static Methods
//**************************************************************************************************

//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************

}
