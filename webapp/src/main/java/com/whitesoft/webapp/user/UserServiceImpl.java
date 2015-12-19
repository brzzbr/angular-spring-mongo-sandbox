package com.whitesoft.webapp.user;

import com.whitesoft.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 18.12.15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UserRepository userRepository;

    public User getUser(String login) {

        return userRepository.findByLogin(login);
    }
}
