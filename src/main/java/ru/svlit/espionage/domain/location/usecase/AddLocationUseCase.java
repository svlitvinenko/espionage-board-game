package ru.svlit.espionage.domain.location.usecase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.svlit.espionage.domain.location.entity.Location;

import java.util.List;

/**
 * Прецедент использования: "Добавление игровой локации".
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
public interface AddLocationUseCase {

    Location addLocation(AddLocationCommand command) throws NotEnoughInfoToCreateLocationException;

    @Getter
    @ToString
    @EqualsAndHashCode
    @RequiredArgsConstructor
    class AddLocationCommand {

        private final String name;
        private final List<Profession> professions;
        private final String avatar;

        @Getter
        @ToString
        @EqualsAndHashCode
        @RequiredArgsConstructor
        public static class Profession {
            private final String name;
        }
    }

    class NotEnoughInfoToCreateLocationException extends Exception {

        public NotEnoughInfoToCreateLocationException(Throwable cause) {
            super(cause);
        }
    }
}
