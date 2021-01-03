package ru.svlit.espionage.api.location.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Запрос на обновление локации.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Update Location Request", description = "Модель запроса на изменение игровой локации")
public class UpdateLocationProfessionsRequest {

    @JsonProperty(required = true)
    @ApiModelProperty(
            example = "1234567890",
            dataType = "string",
            value = "Идентификатор локации"
    )
    private String id;

    @JsonProperty(required = true)
    @ApiModelProperty(value = "Полный актуализированный список профессий")
    private List<Profession> professions;

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(value = "Update Location Request — Profession", description = "Модель профессии для запроса на изменение игровой локации")
    public static class Profession {

        @JsonProperty(required = true)
        @ApiModelProperty(
                example = "Директор фастфуда",
                value = "Название профессии"
        )
        private String name;
    }
}
