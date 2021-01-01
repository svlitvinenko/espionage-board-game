package ru.svlit.espionage.domain.location.port;

import ru.svlit.espionage.domain.location.entity.Location;

/**
 * Порт для добавления новой игровой локации.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface AddLocationPort {

    void addLocation(Location location);
}
