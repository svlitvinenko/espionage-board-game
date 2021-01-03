package ru.svlit.espionage.api.location.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.svlit.espionage.api.location.request.AddLocationRequest;
import ru.svlit.espionage.api.location.request.UpdateLocationProfessionsRequest;
import ru.svlit.espionage.api.location.response.GetLocationResponse;
import ru.svlit.espionage.api.location.response.GetLocationsResponse;
import ru.svlit.espionage.domain.location.entity.Location;
import ru.svlit.espionage.domain.location.usecase.AddLocationUseCase;
import ru.svlit.espionage.domain.location.usecase.AddLocationUseCase.AddLocationCommand;
import ru.svlit.espionage.domain.location.usecase.AddLocationUseCase.NotEnoughInfoToCreateLocationException;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase;
import ru.svlit.espionage.domain.location.usecase.GetLocationByIdUseCase.GetLocationByIdCommand;
import ru.svlit.espionage.domain.location.usecase.GetLocationsUseCase;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

/**
 * Контроллер игровых локаций.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
@Api(value = "Locations API")
class LocationController {

    private final GetLocationsUseCase getLocationsUseCase;
    private final GetLocationByIdUseCase getLocationByIdUseCase;
    private final AddLocationUseCase addLocationUseCase;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Получение деталей локации по её идентификатору", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public ResponseEntity<GetLocationResponse> getLocation(@PathVariable String id) {
        final Optional<Location> locationOptional = getLocationByIdUseCase.getLocationById(new GetLocationByIdCommand(id));

        if (locationOptional.isEmpty()) {
            return notFound().build();
        }

        return ok(convertLocationToResponse(locationOptional.get()));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Получение всех локаций", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public ResponseEntity<GetLocationsResponse> getLocations() {
        final List<Location> locations = getLocationsUseCase.getLocations();
        final GetLocationsResponse response = new GetLocationsResponse(
                locations.stream().map(this::convertLocationToLocationDescription).collect(toUnmodifiableList())
        );
        return ok(response);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Добавление локации", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public ResponseEntity<GetLocationResponse> addLocation(@RequestBody AddLocationRequest request) {
        final AddLocationCommand command = new AddLocationCommand(
                request.getName(),
                request.getProfessions()
                        .stream()
                        .map(p -> new AddLocationCommand.Profession(p.getName()))
                        .collect(toUnmodifiableList()),
                request.getAvatar()
        );

        final Location location;
        try {
            location = addLocationUseCase.addLocation(command);
        } catch (NotEnoughInfoToCreateLocationException e) {
            return badRequest().build();
        }

        return ok(convertLocationToResponse(location));
    }

    @PatchMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Обновление локации", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public ResponseEntity<GetLocationResponse> updateLocationProfessions(@RequestBody UpdateLocationProfessionsRequest request) {
        return ResponseEntity.badRequest().build();
    }

    private static GetLocationResponse convertLocationToResponse(Location location) {
        return new GetLocationResponse(
                new GetLocationResponse.Author(location.getAuthor().getId(), location.getAuthor().getName()),
                location.getAvatar(),
                location.getId(),
                location.getName(),
                location.getProfessions()
                        .stream()
                        .map(p -> new GetLocationResponse.Profession(p.getId(), p.getName()))
                        .collect(toUnmodifiableList())
        );
    }

    private GetLocationsResponse.Location convertLocationToLocationDescription(Location location) {
        return new GetLocationsResponse.Location(
                new GetLocationsResponse.Author(location.getAuthor().getId(), location.getAuthor().getName()),
                location.getAvatar(),
                location.getId(),
                location.getName()
        );
    }
}
