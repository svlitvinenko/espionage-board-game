package ru.svlit.espionage.infrastructure.properties;

/**
 * Интерфейс для унифицированного доступа к настройкам проекта.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
public interface EspionageProperties {

    /**
     * Возвращает строку для соединения с MongoDB.
     *
     * @return строка для соединения с MongoDB.
     */
    String getDatabaseName();

    /**
     * Возвращает строку для соединения с MongoDB.
     *
     * @return строка для соединения с MongoDB.
     */
    String getDatabaseConnectionString();
}
