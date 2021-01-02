package ru.svlit.espionage.api.room.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Запрос на создание комнаты.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CreateRoomRequest {
    private String name;
    private String locationId;
}
