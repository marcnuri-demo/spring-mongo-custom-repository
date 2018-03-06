/*
 * BookRepositoryCustomImpl.java
 *
 * Created on 2018-02-27, 7:30
 */
package com.marcnuri.spring.mongo.customrepository.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
public class BookRepositoryImpl implements BookRepositoryCustom {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	private final MongoTemplate mongoTemplate;

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Autowired
	public BookRepositoryImpl(MongoTemplate mongoTemplate) {
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
		final Query query = new Query();
		final List<Criteria> criteria = new ArrayList<>();
		if(dynamicQuery.getAuthorNameLike() != null) {
			criteria.add(Criteria.where("authorNames").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					dynamicQuery.getAuthorNameLike(), Part.Type.CONTAINING
			), "i"));
		}
		if(dynamicQuery.getPublishDateBefore() != null) {
			criteria.add(Criteria.where("publishDate").lte(dynamicQuery.getPublishDateBefore()));
		}
		if(dynamicQuery.getPublishDateAfter() != null) {
			criteria.add(Criteria.where("publishDate").gte(dynamicQuery.getPublishDateAfter()));
		}
		if(dynamicQuery.getSubject() != null) {
			criteria.add(Criteria.where("subjects").regex(MongoRegexCreator.INSTANCE.toRegularExpression(
					dynamicQuery.getSubject(), Part.Type.SIMPLE_PROPERTY
			), "i"));
		}
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
		}
		return mongoTemplate.find(query, Book.class);
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
