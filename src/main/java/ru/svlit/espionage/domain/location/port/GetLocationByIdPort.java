package ru.svlit.espionage.domain.location.port;

import ru.svlit.espionage.domain.location.entity.Location;

import java.util.Optional;

/**
 * Порт для получения игровой локации по её идентификатору.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface GetLocationByIdPort {

    Optional<Location> getLocationById(String id);
}
