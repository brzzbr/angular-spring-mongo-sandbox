package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.User;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * Interface for user ыукмшсу (BLL).
 *
 * @author brzzbr
 */
public interface UserService {

    User getUser(String login);
}
