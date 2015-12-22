package com.whitesoft.pinmap.user;

import com.whitesoft.pinmap.domain.User;

/**
 * Created by borisbondarenko on 18.12.15.
 */
public interface UserService {

    User getUser(String login);
}
