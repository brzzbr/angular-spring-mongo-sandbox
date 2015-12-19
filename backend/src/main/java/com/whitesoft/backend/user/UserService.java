package com.whitesoft.backend.user;

import com.whitesoft.backend.domain.User;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UserService {

    User getUser(String login);
}
