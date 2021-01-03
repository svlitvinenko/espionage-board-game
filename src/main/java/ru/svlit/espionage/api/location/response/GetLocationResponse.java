
package ru.svlit.espionage.api.location.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(NON_NULL)
@RequiredArgsConstructor
@ApiModel(value = "Get Location Details Response", description = "Модель ответа на запрос деталей игровой локации")
public class GetLocationResponse {

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

    @ApiModelProperty(value = "Профессии локации")
    private final List<Profession> professions;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    @ApiModel(value = "Get Location Details Response — Profession", description = "Модель профессии из ответа на запрос деталей игровой локации")
    public static class Profession {
        @ApiModelProperty(
                example = "1234567890",
                value = "Идентификатор профессии"
        )
        private final String id;

        @ApiModelProperty(
                example = "Директор фастфуда",
                value = "Название профессии"
        )
        private final String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    @ApiModel(value = "Get Location Details Response — Author", description = "Модель создателя из ответа на запрос деталей игровой локации")
    public static class Author {

        @ApiModelProperty(
                example = "1234567890",
                value = "Постоянный идентификатор пользователя"
        )
        private final String id;

        @ApiModelProperty(
                example = "Krosh",
                value = "Актуальное имя пользователя"
        )
        private final String username;
    }
}
