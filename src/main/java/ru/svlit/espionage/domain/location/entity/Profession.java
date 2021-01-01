package ru.svlit.espionage.domain.location.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Профессия на игровой локации.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Profession {
    private final String id;
    private final String name;
}
