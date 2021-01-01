package ru.svlit.espionage.domain.location.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.location.entity.Author;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.entity.Profession;
import ru.svlit.espionage.domain.location.port.AddLocationPort;
import ru.svlit.espionage.domain.location.port.GetLocationByIdPort;
import ru.svlit.espionage.domain.location.usecase.AddLocationUseCase;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.usecase.GetCurrentUserUseCase;

import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * Реализация прецедента использования "Добавление игровой локации".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
public class AddLocationUseCaseImpl implements AddLocationUseCase {

    private final AddLocationPort addLocationPort;
    private final GetLocationByIdPort getLocationByIdPort;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Override
    public Location addLocation(AddLocationCommand command) {
        final String id = UUID.randomUUID().toString();
        final User currentUser = getCurrentUserUseCase.getCurrentUser();
        final Location location = new Location(
                id,
                command.getName(),
                command.getAvatar(),
                command.getProfessions().stream().map(this::generateProfessionFromCommand).collect(toUnmodifiableList()),
                new Author(currentUser.getId(), currentUser.getUsername())
        );
        addLocationPort.addLocation(location);
        final Optional<Location> storedLocation = getLocationByIdPort.getLocationById(id);
        return storedLocation.orElseThrow(IllegalStateException::new);
    }

    private Profession generateProfessionFromCommand(AddLocationCommand.Profession professionFromCommand) {
        return new Profession(
                UUID.randomUUID().toString(),
                professionFromCommand.getName()
        );
    }
}
