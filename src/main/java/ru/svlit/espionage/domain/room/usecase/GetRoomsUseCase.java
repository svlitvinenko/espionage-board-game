package ru.svlit.espionage.domain.room.usecase;

import ru.svlit.espionage.domain.room.entity.Room;

import java.util.List;

/**
 * Прецедент использования: "Получение всех доступных комнат".
 *
 * @author Sergei Litvinenko on 02.01.2021.
 */
public interface GetRoomsUseCase {

    /**
     * Возвращает список доступных комнат. Исключает открытые комнаты, созданные пользователем, обратившимся за
     * списком комнат.
     *
     * @return список комнат.
     */
    List<Room> getRooms();
}
