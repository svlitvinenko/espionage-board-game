package ru.svlit.espionage.domain.user.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Ролевая модель пользователей.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public enum Role implements GrantedAuthority {

    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    public static Role getByName(String name) {
        for (var role : values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Role with name \"" + name + "\" was not found.");
    }
}
