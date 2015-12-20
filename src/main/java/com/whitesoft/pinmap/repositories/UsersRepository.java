package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UsersRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
