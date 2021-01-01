package ru.svlit.espionage.domain.location.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Игровая локация.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Location {
    private final String id;
    private final String name;
    private final String avatar;
    private final List<Profession> professions;
    private final Author author;
}
