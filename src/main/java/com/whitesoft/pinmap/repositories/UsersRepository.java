package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * Mongo repository for users collection
 *
 * @author brzzbr
 */
public interface UsersRepository extends MongoRepository<User, String> {

    /**
     * Returns a user by his login field
     * @param login user's login
     * @return user entity
     */
    User findByLogin(String login);
}
