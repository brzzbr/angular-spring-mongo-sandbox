package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.User;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * Interface for user service (BLL).
 *
 * @author brzzbr
 */
public interface UserService {

    /**
     * get user info by login
     * @param login login
     * @return user object
     */
    User getUser(String login);

    /**
     * get authenticated user
     * @return authenticated user
     */
    User getCurrentUser();
}
