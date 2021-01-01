package ru.svlit.espionage.infrastructure.location;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Модель игровой локации, хранимая в MongoDB.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "locations")
public class LocationModel {
    private final String id;
    private final String name;
    private final String avatar;
    private final List<ProfessionModel> professions;
    private final AuthorModel author;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class ProfessionModel {
        private final String id;
        private final String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class AuthorModel {
        private final String id;
        private final String name;
    }
}
