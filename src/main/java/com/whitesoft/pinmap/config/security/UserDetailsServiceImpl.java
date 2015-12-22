package com.whitesoft.pinmap.config.security;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.user.UserRoleEnum;
import com.whitesoft.pinmap.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Custom {@link UserDetailsService}. Retrieves data from {@link UserService}...
 * from Mongo.
 *
 * @author brzzbr
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    protected UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getUser(s);
        if(user == null) throw new UsernameNotFoundException("User is absent!");

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singleton(new SimpleGrantedAuthority(UserRoleEnum.USER.name()))
        );
    }
}
