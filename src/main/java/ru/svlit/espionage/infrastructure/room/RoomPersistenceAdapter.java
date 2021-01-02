package ru.svlit.espionage.infrastructure.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase.GetLocationByIdCommand;
import ru.svlit.espionage.domain.room.entity.Author;
import ru.svlit.espionage.domain.room.entity.Room;
import ru.svlit.espionage.domain.room.port.GetRoomsPort;
import ru.svlit.espionage.domain.room.port.StoreRoomPort;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.usecase.FindUserByIdUseCase;
import ru.svlit.espionage.domain.user.usecase.FindUserByIdUseCase.FindUserByIdCommand;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * Адаптер для доступа к комнатам.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Slf4j
@Component
@RequiredArgsConstructor
class RoomPersistenceAdapter implements GetRoomsPort, StoreRoomPort {

    private final List<RoomModel> rooms = new CopyOnWriteArrayList<>();
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final GetLocationByIdUseCase getLocationByIdUseCase;

    @Override
    public List<Room> getRooms() {
        final List<Room> rooms = this.rooms.stream().map(this::convertRoomToDomain).collect(toUnmodifiableList());
        log.info("Getting all rooms; size is " + rooms.size());
        return rooms;
    }

    @Override
    public void storeRoom(Room room) {
        log.info("Storing room with name " + room.getName());
        rooms.add(convertRoomFromDomain(room));
        log.info("Storing room with name " + room.getName() + "; rooms amount is now " + rooms.size());
    }

    private Room convertRoomToDomain(RoomModel roomModel) {
        final User author = findUserByIdUseCase.findUserById(new FindUserByIdCommand(roomModel.getAuthorId())).orElseThrow();
        final Location location = getLocationByIdUseCase.getLocationById(new GetLocationByIdCommand(roomModel.getLocationId())).orElseThrow();
        final User locationAuthor = findUserByIdUseCase.findUserById(new FindUserByIdCommand(location.getAuthor().getId())).orElseThrow();
        return new Room(
                roomModel.getId(),
                roomModel.getName(),
                new Author(author.getId(), author.getUsername()),
                new ru.svlit.espionage.domain.room.entity.Location(
                        location.getId(),
                        location.getName(),
                        location.getAvatar(),
                        new Author(locationAuthor.getId(), locationAuthor.getUsername())
                )
        );
    }

    private RoomModel convertRoomFromDomain(Room room) {
        return new RoomModel(
                room.getId(),
                room.getName(),
                room.getAuthor().getId(),
                room.getLocation().getId()
        );
    }
}
