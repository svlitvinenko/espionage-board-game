
package ru.svlit.espionage.api.location.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(NON_NULL)
@RequiredArgsConstructor
@ApiModel(value = "Get Locations Response", description = "Модель ответа на запрос списка игровых локаций")
public class GetLocationsResponse {

    @ApiModelProperty(value = "Локации")
    private final List<Location> locations;


    @Getter
    @ToString
    @ApiIgnore
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    @ApiModel(value = "Get Locations Response — Location", description = "Модель локации из ответа на запрос списка игровых локаций")
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
    @ApiIgnore
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    @ApiModel(value = "Get Locations Response — Author", description = "Модель создателя локации ответа на запрос списка игровых локаций")
    public static class Author {

        @ApiModelProperty(
                example = "1234567890",
                value = "Идентификатор пользователя"
        )
        private final String id;

        @ApiModelProperty(
                example = "Krosh",
                value = "Имя пользователя"
        )
        private final String username;
    }
}
