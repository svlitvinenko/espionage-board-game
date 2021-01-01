package ru.svlit.espionage.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.port.SignUpPort;
import ru.svlit.espionage.domain.user.usecase.FindUserByUsernameUseCase;
import ru.svlit.espionage.domain.user.usecase.FindUserByUsernameUseCase.FindUserByUsernameCommand;
import ru.svlit.espionage.domain.user.usecase.SignUpUseCase;

import java.util.Optional;

/**
 * Реализация прецедента использования "Регистрация пользователя".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {

    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    private final SignUpPort signUpPort;

    @Override
    public String signUp(SignUpCommand command) throws UsernameIsAlreadyTakenException {
        final FindUserByUsernameCommand findUserByUsernameCommand = new FindUserByUsernameCommand(command.getUsername());
        final Optional<User> userByUsernameOptional = findUserByUsernameUseCase.findUserByUsername(findUserByUsernameCommand);

        if (userByUsernameOptional.isPresent()) {
            throw new UsernameIsAlreadyTakenException(command.getUsername());
        }

        final User user = signUpPort.signUp(command.getUsername(), command.getPassword());
        return user.getId();
    }
}
