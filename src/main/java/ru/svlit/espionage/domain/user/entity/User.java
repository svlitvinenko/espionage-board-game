package ru.svlit.espionage.domain.user.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.svlit.espionage.domain.user.entity.Role;

import java.util.Collection;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Пользователь системы.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class User implements UserDetails {

    private final String id;
    private final String username;
    private final String password;
    private final Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return unmodifiableSet(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
