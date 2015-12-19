package com.whitesoft.webapp.repositories;

import com.whitesoft.webapp.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
