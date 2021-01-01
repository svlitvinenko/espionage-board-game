package ru.svlit.espionage.domain.user.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.svlit.espionage.domain.user.entity.User;

/**
 * Реализация прецедента использования "Получение данных о текущем пользователе".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Service
@RequiredArgsConstructor
class GetCurrentUserUseCaseImpl implements GetCurrentUserUseCase {
    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new IllegalStateException("Could not retrieve current user.");
        }
    }
}
