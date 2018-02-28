/*
 * MongoConfiguration.java
 *
 * Created on 2018-02-27, 7:02
 */
package com.marcnuri.spring.mongo.customrepository;

import com.marcnuri.spring.mongo.customrepository.book.BookRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

/**
 * Mongo Spring configuration that creates a MongoDB process listening in a random port
 * and attaches the {@link Mongo} connection to this process.
 * <p>
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
@Configuration
@EnableMongoRepositories(
		basePackageClasses = {
				BookRepository.class
		})
@Import({MongoProperties.class, EmbeddedMongoProperties.class})
public class EmbeddedMongoConfiguration extends AbstractMongoConfiguration {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	private static final String DATABASE_NAME = "marcnuri_demo";

	private Mongo mongo;
	private final Environment environment;
	private final MongoProperties properties;
	private MongoClientOptions options;


//**************************************************************************************************
//  Constructors
//**************************************************************************************************
	@Autowired
	public EmbeddedMongoConfiguration(Environment environment,
									  MongoProperties properties) {
		this.environment = environment;
		this.properties = properties;
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

	@Bean(destroyMethod = "close")
	@Primary
	public MongoClient mongo(MongodProcess mongodProcess) throws Exception {
		Net net = mongodProcess.getConfig().net();
		properties.setHost(net.getServerAddress().getHostName());
		properties.setPort(net.getPort());
		return properties.createMongoClient(this.options, this.environment);

	}

	@Bean(destroyMethod = "stop")
	public MongodProcess mongodProcess(MongodExecutable mongodExecutable) throws IOException {
		return mongodExecutable.start();
	}

	@Bean(destroyMethod = "stop")
	public MongodExecutable mongodExecutable(MongodStarter mongodStarter, IMongodConfig mongodConfig) throws IOException {
		return mongodStarter.prepare(mongodConfig);
	}

	@Bean
	public IMongodConfig mongodConfig() throws IOException {
		// https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo/issues/166
		// Storage engine fixes slow runs  on TeamCity
		return new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.cmdOptions(
						new MongoCmdOptionsBuilder()
								.useStorageEngine("mmapv1")
								.build()
				)
				.build();
	}

	@Bean
	public MongodStarter mongodStarter() {
		return MongodStarter.getDefaultInstance();
	}

//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************
	@Autowired(required = false)
	public void setOptions(MongoClientOptions options) {
		this.options = options;
	}

	@Autowired
	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}
//**************************************************************************************************
//  Static Methods
//**************************************************************************************************

//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************

}
