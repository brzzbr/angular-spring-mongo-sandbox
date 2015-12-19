package com.whitesoft.webapp.security;

import com.whitesoft.webapp.user.User;
import com.whitesoft.webapp.user.UserRoleEnum;
import com.whitesoft.webapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    protected UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getUser(s);
        if(user == null) return null;

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
