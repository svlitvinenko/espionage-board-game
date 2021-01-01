package ru.svlit.espionage.api.user.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Модель ответа на запрос на поиск пользователя по его идентификатору.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class FindUserByIdResponse {
    private final String id;
    private final String username;
}
