package com.whitesoft.webapp.user;

import com.whitesoft.webapp.domain.User;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UserService {

    User getUser(String login);
}
