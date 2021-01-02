package ru.svlit.espionage.infrastructure.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий пользователей.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Repository
interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByUsername(String username);
}
