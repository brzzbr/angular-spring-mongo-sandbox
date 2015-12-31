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
     * Returns a user by his username field
     * @param username user's username
     * @return user entity
     */
    User findByUsername(String username);
}
