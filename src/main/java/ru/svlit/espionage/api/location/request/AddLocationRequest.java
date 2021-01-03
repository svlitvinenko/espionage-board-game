package ru.svlit.espionage.api.location.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Запрос на добавление локации.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Add Location Request", description = "Модель запроса на создание игровой локации")
public class AddLocationRequest {

    @JsonProperty(required = true)
    @ApiModelProperty(
            example = "Торговый центр",
            value = "Придуманное пользователем название игровой локации"
    )
    private String name;

    @JsonProperty(required = true)
    @ApiModelProperty(value = "Набор профессий для игровой локации")
    private List<Profession> professions;

    @JsonProperty
    @ApiModelProperty(
            example = "https://some.jpeg",
            value = "Ссылка на фоновое изображение для локации"
    )
    private String avatar;

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(
            value = "Add Location Request — Profession",
            description = "Модель профессии для запроса на создание игровой локации"
    )
    public static class Profession {

        @JsonProperty(required = true)
        @ApiModelProperty(
                example = "Директор фастфуда",
                value = "Название профессии, придуманное пользователем"
        )
        private String name;
    }
}
