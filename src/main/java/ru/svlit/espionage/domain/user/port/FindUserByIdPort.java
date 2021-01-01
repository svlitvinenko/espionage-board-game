package ru.svlit.espionage.domain.user.port;


import ru.svlit.espionage.domain.user.entity.User;

import java.util.Optional;

/**
 * Порт поиска пользователей по идентификатору.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface FindUserByIdPort {

    Optional<User> findUserById(String id);
}
