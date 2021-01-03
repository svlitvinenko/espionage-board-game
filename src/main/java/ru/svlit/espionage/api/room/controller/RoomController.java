package ru.svlit.espionage.api.room.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;
import ru.svlit.espionage.api.room.request.CreateRoomRequest;
import ru.svlit.espionage.api.room.response.RoomsResponse;
import ru.svlit.espionage.domain.room.entity.Room;
import ru.svlit.espionage.domain.room.usecase.CreateRoomUseCase;
import ru.svlit.espionage.domain.room.usecase.CreateRoomUseCase.CreateRoomCommand;
import ru.svlit.espionage.domain.room.usecase.CreateRoomUseCase.NotEnoughInfoToCreateRoomException;
import ru.svlit.espionage.domain.room.usecase.GetRoomsUseCase;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Контроллер комнат.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Api(value = "Rooms API")
class RoomController {

    private final List<SseEmitter> sseEmitters = new CopyOnWriteArrayList<>();

    private final CreateRoomUseCase createRoomUseCase;
    private final GetRoomsUseCase getRoomsUseCase;

    @RequestMapping(value = "/observe")
    @ApiOperation(value = "Подписка на открытые комнаты", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public SseEmitter subscribeToRooms() {
        log.info("Request: subscribe to rooms list");
        final SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> sseEmitters.remove(emitter));
        sseEmitters.add(emitter);
        final List<Room> rooms = getRoomsUseCase.getRooms();
        final RoomsResponse response = convertFromRoomsToResponse(rooms);
        createAndSendRoomsEvent(emitter, response);
        return emitter;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Получение всех комнат")
    public ResponseEntity<RoomsResponse> getRooms() {
        final List<Room> rooms = getRoomsUseCase.getRooms();
        final RoomsResponse response = convertFromRoomsToResponse(rooms);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation(value = "Создание комнаты", authorizations = {@Authorization("USER"), @Authorization("ADMIN")})
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest request) {
        log.info("Request: create room with name " + request.getName());
        try {
            createRoomUseCase.createRoom(new CreateRoomCommand(request.getName(), request.getLocationId()));
            log.info("Room with name " + request.getName() + " created successfully");
        } catch (NotEnoughInfoToCreateRoomException e) {
            return badRequest().build();
        }

        log.info("Notifying subscribers about updated rooms list");
        final List<Room> rooms = getRoomsUseCase.getRooms();
        final RoomsResponse response = convertFromRoomsToResponse(rooms);
        for (SseEmitter emitter : sseEmitters) {
            createAndSendRoomsEvent(emitter, response);
        }

        return ok().build();
    }

    private void createAndSendRoomsEvent(SseEmitter emitter, RoomsResponse response) {
        final SseEventBuilder roomsEvent = SseEmitter.event().name("rooms").data(response);
        try {
            emitter.send(roomsEvent);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private RoomsResponse convertFromRoomsToResponse(List<Room> rooms) {
        log.info("Converting rooms for response; list length is " + rooms.size());
        return new RoomsResponse(
                rooms.stream().map(this::convertRoomToResponseRoom).collect(toUnmodifiableList())
        );
    }

    private RoomsResponse.Room convertRoomToResponseRoom(Room room) {
        return new RoomsResponse.Room(
                room.getId(),
                room.getName(),
                new RoomsResponse.Author(room.getAuthor().getId(), room.getAuthor().getName()),
                new RoomsResponse.Location(
                        new RoomsResponse.Author(room.getLocation().getAuthor().getId(), room.getLocation().getAuthor().getName()),
                        room.getLocation().getAvatar(),
                        room.getLocation().getId(),
                        room.getLocation().getName()
                )
        );
    }
}
