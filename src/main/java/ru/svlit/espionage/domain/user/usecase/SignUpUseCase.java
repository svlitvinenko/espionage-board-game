package ru.svlit.espionage.domain.user.usecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Сценарий использования: "Регистрация пользователя".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface SignUpUseCase {

    String signUp(SignUpCommand command) throws UsernameIsAlreadyTakenException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class SignUpCommand {
        private final String username;
        private final String password;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    class UsernameIsAlreadyTakenException extends Exception {
        private final String username;

        @Override
        public String getMessage() {
            return "Username \"" + username + "\" is already taken.";
        }
    }
}
