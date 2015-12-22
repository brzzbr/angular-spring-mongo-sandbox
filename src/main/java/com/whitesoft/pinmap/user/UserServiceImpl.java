package com.whitesoft.pinmap.user;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 18.12.15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UsersRepository usersRepository;

    public User getUser(String login) {

        return usersRepository.findByLogin(login);
    }
}
