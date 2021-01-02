package ru.svlit.espionage.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.svlit.espionage.domain.user.entity.Role;
import ru.svlit.espionage.domain.user.entity.User;
import ru.svlit.espionage.domain.user.port.FindUserByIdPort;
import ru.svlit.espionage.domain.user.port.FindUserByUsernamePort;
import ru.svlit.espionage.domain.user.port.SignUpPort;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static ru.svlit.espionage.domain.user.entity.Role.USER;

/**
 * Адаптер для доступа к сохранённым данным о пользователях.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Component
@RequiredArgsConstructor
class UserPersistenceAdapter implements FindUserByIdPort, FindUserByUsernamePort, SignUpPort, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserById(String id) {
        final Optional<UserModel> userModelOptional = userRepository.findById(id);
        return userModelOptional.map(this::convertUserModelToDomain);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        final Optional<UserModel> userModelOptional = userRepository.findByUsername(username);
        return userModelOptional.map(this::convertUserModelToDomain);
    }

    @Override
    public User signUp(String username, String password) {
        final String id = UUID.randomUUID().toString();
        final User user = new User(id, username, password, Set.of(USER));
        final UserModel userModelToStore = convertUserModelFromDomain(user);
        final UserModel storedUserModel = userRepository.save(userModelToStore);
        return convertUserModelToDomain(storedUserModel);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userOptional = findUserByUsername(username);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private User convertUserModelToDomain(UserModel userModel) {
        return new User(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getRoles().stream().map(Role::getByName).collect(toUnmodifiableSet())
        );
    }

    private UserModel convertUserModelFromDomain(User user) {
        return new UserModel(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::name).collect(toUnmodifiableSet())
        );
    }
}
