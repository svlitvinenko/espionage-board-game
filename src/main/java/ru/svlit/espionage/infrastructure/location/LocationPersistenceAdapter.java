package ru.svlit.espionage.infrastructure.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.svlit.espionage.domain.location.entity.Author;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.entity.Profession;
import ru.svlit.espionage.domain.location.port.AddLocationPort;
import ru.svlit.espionage.domain.location.port.GetLocationByIdPort;
import ru.svlit.espionage.domain.location.port.GetLocationsPort;
import ru.svlit.espionage.infrastructure.location.LocationModel.AuthorModel;
import ru.svlit.espionage.infrastructure.location.LocationModel.ProfessionModel;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * Адаптер для доступа к постоянному хранилищу данных об игровых локациях.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Component
@RequiredArgsConstructor
class LocationPersistenceAdapter implements GetLocationsPort, GetLocationByIdPort, AddLocationPort {

    private final LocationRepository locationRepository;

    @Override
    public Optional<Location> getLocationById(String id) {
        return locationRepository.findById(id).map(this::convertLocationModelToDomain);
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.findAll().stream().map(this::convertLocationModelToDomain).collect(toUnmodifiableList());
    }

    @Override
    public void addLocation(Location location) {
        locationRepository.save(convertLocationFromDomain(location));
    }

    private Location convertLocationModelToDomain(LocationModel locationModel) {
        return new Location(
                locationModel.getId(),
                locationModel.getName(),
                locationModel.getAvatar(),
                locationModel.getProfessions().stream().map(this::convertProfessionModelToDomain).collect(toUnmodifiableList()),
                new Author(locationModel.getAuthor().getId(), locationModel.getAuthor().getName())
        );
    }

    private LocationModel convertLocationFromDomain(Location location) {
        return new LocationModel(
                location.getId(),
                location.getName(),
                location.getAvatar(),
                location.getProfessions().stream().map(this::convertProfessionFromDomain).collect(toUnmodifiableList()),
                new AuthorModel(location.getAuthor().getId(), location.getAuthor().getName())
        );
    }

    private Profession convertProfessionModelToDomain(ProfessionModel professionModel) {
        return new Profession(
                professionModel.getId(),
                professionModel.getName()
        );
    }

    private ProfessionModel convertProfessionFromDomain(Profession profession) {
        return new ProfessionModel(
                profession.getId(),
                profession.getName()
        );
    }
}
