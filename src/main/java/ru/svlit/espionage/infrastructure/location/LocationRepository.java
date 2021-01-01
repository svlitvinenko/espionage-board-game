package ru.svlit.espionage.infrastructure.location;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий игровых локаций в MongoDB.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Repository
public interface LocationRepository extends MongoRepository<LocationModel, String> {
}
