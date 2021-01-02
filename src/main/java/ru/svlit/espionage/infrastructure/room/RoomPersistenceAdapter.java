package ru.svlit.espionage.infrastructure.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.svlit.espionage.domain.room.entity.Room;
import ru.svlit.espionage.domain.room.port.GetRoomsPort;
import ru.svlit.espionage.domain.room.port.StoreRoomPort;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Collections.unmodifiableList;

/**
 * Адаптер для доступа к комнатам.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class RoomPersistenceAdapter implements GetRoomsPort, StoreRoomPort {

    private final List<Room> rooms = new CopyOnWriteArrayList<>();

    @Override
    public List<Room> getRooms() {
        final List<Room> rooms = unmodifiableList(this.rooms);
        log.info("Getting all rooms; size is " + rooms.size());
        return rooms;
    }

    @Override
    public void storeRoom(Room room) {
        log.info("Storing room with name " + room.getName());
        rooms.add(room);
        log.info("Storing room with name " + room.getName() + "; rooms amount is now " + rooms.size());
    }
}
