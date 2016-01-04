package com.whitesoft.pinmap.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Spring context configuration for persistence layer. In the project
 * I intended to use a MongoDB in this role, so configuration
 * sets host, port, db name and returns a Mongo object.
 *
 * @author brzzbr
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.whitesoft.pinmap.repositories")
@PropertySource({ "classpath:database.properties" })
public class RepositoryConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.host: localhost}")
    private String host;

    @Value("${mongo.port: 27017}")
    private int port;

    @Value("${mongo.db: local}")
    private String database;

    @Override
    protected String getDatabaseName() {

        return database;
    }

    @Override
    public Mongo mongo() throws Exception {

        return new MongoClient(host, port);
    }
}
