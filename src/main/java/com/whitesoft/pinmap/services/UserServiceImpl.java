package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * Implementation of user service. Simply retrieves data from {@link UsersRepository}.
 *
 * @author brzzbr
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    protected UsersRepository usersRepository;

    public User getUser(String login) {

        return usersRepository.findByUsername(login);
    }

    @Override
    public User getCurrentUser() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser =  authentication.getName();
        return getUser(currentUser);
    }
}
