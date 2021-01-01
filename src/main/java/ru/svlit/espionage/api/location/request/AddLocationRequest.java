package ru.svlit.espionage.api.location.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class AddLocationRequest {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private List<Profession> professions;

    @JsonProperty
    private String avatar;

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Profession {

        @JsonProperty(required = true)
        private String name;
    }
}
