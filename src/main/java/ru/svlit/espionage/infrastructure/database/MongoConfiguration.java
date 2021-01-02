package ru.svlit.espionage.infrastructure.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import ru.svlit.espionage.infrastructure.properties.EspionageProperties;

/**
 * Конфигурация MongoDB.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
@Configuration
@RequiredArgsConstructor
class MongoConfiguration extends AbstractMongoClientConfiguration {

    private final EspionageProperties properties;

    @Override
    protected String getDatabaseName() {
        return properties.getDatabaseName();
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(properties.getDatabaseConnectionString());
    }
}
