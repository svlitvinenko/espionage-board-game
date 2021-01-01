package ru.svlit.espionage.domain.user.port;

import ru.svlit.espionage.domain.user.entity.User;

/**
 * Порт регистрации пользователя.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface SignUpPort {

    User signUp(String username, String password);
}
