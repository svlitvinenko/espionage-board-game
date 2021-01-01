package ru.svlit.espionage.domain.location.port;

import ru.svlit.espionage.domain.location.entity.Location;

import java.util.List;

/**
 * Порт для получения списка игровых локаций.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface GetLocationsPort {

    List<Location> getLocations();
}
