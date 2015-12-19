package com.whitesoft.webapp.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.whitesoft.webapp.repositories")
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
