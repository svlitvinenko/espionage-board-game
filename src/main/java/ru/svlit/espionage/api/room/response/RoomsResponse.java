package ru.svlit.espionage.api.room.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Get Rooms Response", description = "Модель ответа на запрос списка комнат")
public class RoomsResponse {

    @ApiModelProperty(value = "Список открытых комнат, новые сначала")
    private final List<Room> rooms;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    @ApiModel(value = "Get Rooms Response — Room", description = "Модель комнаты из ответа на запрос списка комнат")
    public static class Room {

        @ApiModelProperty(
                example = "1234567890",
                value = "Идентификатор комнаты"
        )
        private final String id;

        @ApiModelProperty(
                example = "Kikoriki",
                value = "Название комнаты"
        )
        private final String name;

        @ApiModelProperty(value = "Создатель комнаты")
        private final Author author;

        @ApiModelProperty(value = "Игровая локация комнаты")
        private final Location location;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    @ApiModel(
            value = "Get Rooms Response — Location",
            description = "Модель локации комнаты из ответа на запрос списка комнат"
    )
    public static class Location {
        @ApiModelProperty(value = "Создатель локации")
        private final Author author;

        @ApiModelProperty(
                example = "https://some.jpeg",
                value = "Фоновое изображение локации"
        )
        private final String avatar;

        @ApiModelProperty(
                example = "1234567890",
                value = "Идентификатор локации"
        )
        private final String id;

        @ApiModelProperty(
                example = "Торговый центр",
                value = "Название локации"
        )
        private final String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    @ApiModel(
            value = "Get Rooms Response — Author",
            description = "Модель автора комнаты/локации из ответа на запрос списка комнат"
    )
    public static class Author {

        @ApiModelProperty(
                example = "1234567890",
                value = "Постоянный идентификатор автора"
        )
        private final String id;

        @ApiModelProperty(
                example = "Krosh",
                value = "Актуальное имя автора"
        )
        private final String username;
    }
}
