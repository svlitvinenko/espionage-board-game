package ru.svlit.espionage.domain.user.usecase;

import ru.svlit.espionage.domain.user.entity.User;

/**
 * Прецедент использования: "Получение информации о текущем пользователе".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface GetCurrentUserUseCase {

    User getCurrentUser() throws CurrentUserIsUnauthorizedException;

    class CurrentUserIsUnauthorizedException extends Exception {
    }
}
