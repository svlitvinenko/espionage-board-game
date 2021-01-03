package ru.svlit.espionage.api.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Find User by ID Request", description = "Модель запроса на поиск пользователя по его имени")
public class FindUserByUsernameRequest {

    @JsonProperty(required = true)
    @ApiModelProperty(
            example = "1234567890",
            value = "Идентификатор пользователя"
    )
    private String username;
}
