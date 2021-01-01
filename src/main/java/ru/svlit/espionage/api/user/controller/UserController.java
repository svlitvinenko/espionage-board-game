package ru.svlit.espionage.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.svlit.espionage.api.user.request.FindUserByIdRequest;
import ru.svlit.espionage.api.user.request.FindUserByUsernameRequest;
import ru.svlit.espionage.api.user.request.SignUpRequest;
import ru.svlit.espionage.api.user.response.FindUserByIdResponse;
import ru.svlit.espionage.api.user.response.SignUpResponse;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.usecase.FindUserByIdUseCase;
import ru.svlit.espionage.domain.user.usecase.FindUserByIdUseCase.FindUserByIdCommand;
import ru.svlit.espionage.domain.user.usecase.FindUserByUsernameUseCase;
import ru.svlit.espionage.domain.user.usecase.FindUserByUsernameUseCase.FindUserByUsernameCommand;
import ru.svlit.espionage.domain.user.usecase.SignUpUseCase;
import ru.svlit.espionage.domain.user.usecase.SignUpUseCase.UsernameIsAlreadyTakenException;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

/**
 * Контроллер для работы с пользователями.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {

    private final FindUserByIdUseCase findUserByIdUseCase;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    private final SignUpUseCase signUpUseCase;

    @GetMapping("/id")
    public ResponseEntity<FindUserByIdResponse> findUserById(@RequestBody FindUserByIdRequest request) {
        final String id = request.getId();
        final FindUserByIdCommand command = new FindUserByIdCommand(id);
        final Optional<User> userOptional = findUserByIdUseCase.findUserById(command);

        return userOptional
                .map(user -> ok(new FindUserByIdResponse(user.getId(), user.getUsername())))
                .orElseGet(() -> notFound().build());
    }

    @GetMapping("/username")
    public ResponseEntity<FindUserByIdResponse> findUserByUsername(@RequestBody FindUserByUsernameRequest request) {
        final String username = request.getUsername();
        final FindUserByUsernameCommand command = new FindUserByUsernameCommand(username);
        final Optional<User> userOptional = findUserByUsernameUseCase.findUserByUsername(command);

        return userOptional
                .map(user -> ok(new FindUserByIdResponse(user.getId(), user.getUsername())))
                .orElseGet(() -> notFound().build());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) {
        final SignUpUseCase.SignUpCommand command = new SignUpUseCase.SignUpCommand(request.getUsername(), request.getPassword());

        try {
            final String userId = signUpUseCase.signUp(command);
            return ok(new SignUpResponse(userId));
        } catch (UsernameIsAlreadyTakenException e) {
            return badRequest().build();
        }
    }
}
