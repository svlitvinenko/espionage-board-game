package ru.svlit.espionage.domain.room.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
    private final Author author;
}
