package ru.svlit.espionage.infrastructure.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Класс для унифицированного доступа к настройкам проекта.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
@Getter
@Setter
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "espionage")
class EspionagePropertiesImpl implements EspionageProperties {

    private String databaseName;
    private String databaseConnectionString;
}
