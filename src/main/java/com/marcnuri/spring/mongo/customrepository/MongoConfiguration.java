/*
 * MongoConfiguration.java
 *
 * Created on 2018-02-27, 7:02
 */
package com.marcnuri.spring.mongo.customrepository;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
@Configuration
@EnableMongoRepositories(
		basePackageClasses = {
				MongoCustomRepositoryApplication.class
		})
public class MongoConfiguration extends AbstractMongoConfiguration {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	private static final String DATABASE_NAME = "marcnuri_demo";
	private final Mongo mongo;

//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Autowired
	public MongoConfiguration(Mongo mongo) {
		this.mongo = mongo;
	}

//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************

//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************
	@Override
	protected String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongo;
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
