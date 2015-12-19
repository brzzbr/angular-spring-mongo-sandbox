package com.whitesoft.webapp.repositories;

import com.whitesoft.webapp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
