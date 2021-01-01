
package ru.svlit.espionage.api.location.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(NON_NULL)
@RequiredArgsConstructor
public class GetLocationsResponse {
    private final List<Location> locations;


    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    public static class Location {
        private final Author author;
        private final String avatar;
        private final String id;
        private final String name;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @JsonInclude(NON_NULL)
    @RequiredArgsConstructor
    public static class Author {
        private final String id;
        private final String username;
    }
}
