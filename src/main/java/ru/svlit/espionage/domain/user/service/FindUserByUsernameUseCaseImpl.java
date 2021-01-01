package ru.svlit.espionage.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.port.FindUserByUsernamePort;
import ru.svlit.espionage.domain.user.usecase.FindUserByUsernameUseCase;

import java.util.Optional;

/**
 * Реализация сценария использования "Поиск пользователя по имени пользователя".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
class FindUserByUsernameUseCaseImpl implements FindUserByUsernameUseCase {

    private final FindUserByUsernamePort findUserByUsernamePort;

    @Override
    public Optional<User> findUserByUsername(FindUserByUsernameCommand command) {
        return findUserByUsernamePort.findUserByUsername(command.getUsername());
    }
}
