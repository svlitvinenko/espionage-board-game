package ru.svlit.espionage.api.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Модель запроса на поиск пользователя по его имени.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindUserByUsernameRequest {

    @JsonProperty(required = true)
    private String username;
}
