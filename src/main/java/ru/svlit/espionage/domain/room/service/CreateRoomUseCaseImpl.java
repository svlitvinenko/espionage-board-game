package ru.svlit.espionage.domain.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase.GetLocationByIdCommand;
import ru.svlit.espionage.domain.room.entity.Author;
import ru.svlit.espionage.domain.room.entity.Room;
import ru.svlit.espionage.domain.room.port.StoreRoomPort;
import ru.svlit.espionage.domain.room.usecase.CreateRoomUseCase;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.usecase.GetCurrentUserUseCase;
import ru.svlit.espionage.domain.user.usecase.GetCurrentUserUseCase.CurrentUserIsUnauthorizedException;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Реализация прецедента использования "Добавление комнаты".
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final StoreRoomPort storeRoomPort;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetLocationByIdUseCase getLocationByIdUseCase;

    @Override
    public Room createRoom(CreateRoomCommand command) throws NotEnoughInfoToCreateRoomException {
        try {
            final User currentUser = getCurrentUserUseCase.getCurrentUser();
            final Location location = getLocationByIdUseCase.getLocationById(new GetLocationByIdCommand(command.getLocationId())).orElseThrow();
            final Room room = createRoom(command, currentUser, location);
            storeRoomPort.storeRoom(room);
            return room;
        } catch (CurrentUserIsUnauthorizedException | NoSuchElementException e) {
            throw new NotEnoughInfoToCreateRoomException(e);
        }
    }

    private Room createRoom(CreateRoomCommand command, User currentUser, Location location) {
        return new Room(
                UUID.randomUUID().toString(),
                command.getName(),
                new Author(currentUser.getId(), currentUser.getUsername()),
                new ru.svlit.espionage.domain.room.entity.Location(
                        command.getLocationId(),
                        location.getName(),
                        location.getAvatar(),
                        new Author(location.getAuthor().getId(), location.getAuthor().getName())
                )
        );
    }
}
