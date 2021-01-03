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
 * Модель запроса на поиск пользователя по его идентификатору.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Sign Up Request", description = "Модель запроса на регистрацию пользователя")
public class SignUpRequest {

    @JsonProperty(required = true)
    @ApiModelProperty(
            example = "Krosh",
            value = "Имя пользователя"
    )
    private String username;

    @JsonProperty(required = true)
    @ApiModelProperty(
            example = "s0m3g00DpassW0rD",
            value = "Пароль"
    )
    private String password;
}
