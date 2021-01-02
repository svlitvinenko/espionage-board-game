package ru.svlit.espionage.api.room.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Ответ на запрос списка комнат.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class RoomsResponse {
    private final List<Room> rooms;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    public static class Room {
        private final String id;
        private final String name;
        private final Author author;
        private final Location location;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    public static class Location {
        private final Author author;
        private final String avatar;
        private final String id;
        private final String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    public static class Author {
        private final String id;
        private final String username;
    }
}
