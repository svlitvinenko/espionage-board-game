package ru.svlit.espionage.domain.location.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.port.GetLocationByIdPort;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase;

import java.util.Optional;

/**
 * Реализация прецедента использования {@link GetLocationByIdUseCase}.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
public class GetLocationByIdUseCaseImpl implements GetLocationByIdUseCase {

    private final GetLocationByIdPort getLocationByIdPort;

    @Override
    public Optional<Location> getLocationById(GetLocationByIdCommand command) {
        return getLocationByIdPort.getLocationById(command.getId());
    }
}
