package ru.svlit.espionage.api.user.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Модель ответа на запрос на поиск пользователя по его имени.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@ApiModel(value = "Find User by Username Response", description = "Модель ответа на запрос пользователя по его имени")
public class FindUserByUsernameResponse {

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
