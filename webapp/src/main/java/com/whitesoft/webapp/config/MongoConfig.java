package com.whitesoft.webapp.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.whitesoft.webapp.repositories")
@PropertySource({ "classpath:database.properties" })
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    protected Environment env;

    @Override
    protected String getDatabaseName() {

        return env.getProperty("mongo.db", "local");
    }

    @Override
    public Mongo mongo() throws Exception {

        String host = env.getProperty("mongo.host", "localhost");
        Integer port = Integer.valueOf(env.getProperty("mongo.port", "27017"));

        return new MongoClient(host, port);
    }
}
