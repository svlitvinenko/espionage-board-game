package ru.svlit.espionage.domain.location.usecase;

import ru.svlit.espionage.domain.location.entity.Location;

import java.util.List;

/**
 * Прецедент использования: "Получение списка игровых локаций".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface GetLocationsUseCase {

    List<Location> getLocations();
}
