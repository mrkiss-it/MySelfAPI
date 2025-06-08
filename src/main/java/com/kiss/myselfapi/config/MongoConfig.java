package com.kiss.myselfapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "MySelf";
    }

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    public MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .build();
    }
}