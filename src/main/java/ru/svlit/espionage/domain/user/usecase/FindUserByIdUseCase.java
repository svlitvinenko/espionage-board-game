package ru.svlit.espionage.domain.user.usecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.espionage.domain.user.entity.User;

import java.util.Optional;

/**
 * Сценарий использования: "Поиск пользователя по идентификатору".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface FindUserByIdUseCase {

    /**
     * Находит пользователя по данным из команды.
     *
     * @param command команда на поиск пользователя.
     * @return пользователь или {@link Optional#empty()}, если он не найден или по иным причинам (например,
     * низкий уровень доступа)
     */
    Optional<User> findUserById(FindUserByIdCommand command);

    /**
     * Команда на поиск пользователя.
     */
    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class FindUserByIdCommand {
        private final String id;
    }
}
