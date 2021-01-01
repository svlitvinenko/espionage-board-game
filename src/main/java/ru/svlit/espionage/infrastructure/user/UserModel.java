package ru.svlit.espionage.infrastructure.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Модель пользователя для хранения в БД MongoDB.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Document(collection = "users")
public class UserModel {
    private final String id;
    private final String username;
    private final String password;
    private final Set<String> roles;
}
