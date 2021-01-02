package ru.svlit.espionage.domain.room.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Модель игровой комнаты.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Room {
    private final String id;
    private final String name;
    private final Author author;
    private final Location location;
}
