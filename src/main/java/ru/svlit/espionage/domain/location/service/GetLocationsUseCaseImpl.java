package ru.svlit.espionage.domain.location.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.port.GetLocationsPort;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase;
import ru.svlit.espionage.domain.location.usecase.GetLocationsUseCase;

import java.util.List;

/**
 * Реализация прецедента использования {@link GetLocationByIdUseCase}.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
class GetLocationsUseCaseImpl implements GetLocationsUseCase {

    private final GetLocationsPort getLocationsPort;

    @Override
    public List<Location> getLocations() {
        return getLocationsPort.getLocations();
    }
}
