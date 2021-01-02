package ru.svlit.espionage.domain.room.port;

import ru.svlit.espionage.domain.room.entity.Room;

/**
 * Порт сохранения комнаты.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
public interface StoreRoomPort {

    void storeRoom(Room room);
}
