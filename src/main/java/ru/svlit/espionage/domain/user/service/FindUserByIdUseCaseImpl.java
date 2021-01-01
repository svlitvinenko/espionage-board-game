package ru.svlit.espionage.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.port.FindUserByIdPort;
import ru.svlit.espionage.domain.user.usecase.FindUserByIdUseCase;

import java.util.Optional;

/**
 * Реализация сценария использования "Поиск пользователя по идентификатору".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final FindUserByIdPort findUserByIdPort;

    @Override
    public Optional<User> findUserById(FindUserByIdCommand command) {
        return findUserByIdPort.findUserById(command.getId());
    }
}
