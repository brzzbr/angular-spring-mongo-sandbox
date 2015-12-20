package com.whitesoft.backend.repositories;

import com.whitesoft.backend.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UsersRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
