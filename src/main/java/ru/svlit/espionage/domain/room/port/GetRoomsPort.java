package ru.svlit.espionage.domain.room.port;

import ru.svlit.espionage.domain.room.entity.Room;

import java.util.List;

/**
 * Порт доступа к списку всех существующих комнат.
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
public interface GetRoomsPort {

    List<Room> getRooms();
}
