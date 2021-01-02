package ru.svlit.espionage.domain.room.usecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.espionage.domain.room.entity.Room;

/**
 * Прецедент использования "Создание комнаты".
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
public interface CreateRoomUseCase {

    Room createRoom(CreateRoomCommand command) throws NotEnoughInfoToCreateRoomException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class CreateRoomCommand {
        private final String name;
        private final String locationId;
    }

    class NotEnoughInfoToCreateRoomException extends Exception {

        public NotEnoughInfoToCreateRoomException(Throwable cause) {
            super(cause);
        }
    }
}
