package ru.svlit.espionage.domain.location.usecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.espionage.domain.location.entity.Location;

import java.util.Optional;

/**
 * Прецедент использования: "Получение списка игровых локаций".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface GetLocationByIdUseCase {

    Optional<Location> getLocationById(GetLocationByIdCommand command);

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class GetLocationByIdCommand {
        private final String id;
    }
}
