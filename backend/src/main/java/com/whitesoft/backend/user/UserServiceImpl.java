package com.whitesoft.backend.user;

import com.whitesoft.backend.domain.User;
import com.whitesoft.backend.repositories.UserRepository;
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
