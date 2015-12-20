package com.whitesoft.backend.user;

import com.whitesoft.backend.domain.User;
import com.whitesoft.backend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
