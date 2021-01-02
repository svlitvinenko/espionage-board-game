package ru.svlit.espionage.infrastructure.room;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Модель для хранения комнаты.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RoomModel {
    private final String id;
    private final String name;
    private final String authorId;
    private final String locationId;
}
