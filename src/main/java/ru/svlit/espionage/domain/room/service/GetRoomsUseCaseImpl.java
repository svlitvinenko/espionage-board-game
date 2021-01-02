package ru.svlit.espionage.domain.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.room.entity.Room;
import ru.svlit.espionage.domain.room.port.GetRoomsPort;
import ru.svlit.espionage.domain.room.usecase.GetRoomsUseCase;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Реализация прецедента использования "Получение доступных комнат".
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
@Slf4j
@Service
@RequiredArgsConstructor
class GetRoomsUseCaseImpl implements GetRoomsUseCase {

    private final GetRoomsPort getRoomsPort;

    @Override
    public List<Room> getRooms() {
        return unmodifiableList(getRoomsPort.getRooms());
    }
}
