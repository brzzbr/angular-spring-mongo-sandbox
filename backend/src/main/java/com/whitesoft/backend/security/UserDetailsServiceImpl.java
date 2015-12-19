package com.whitesoft.backend.security;

import com.whitesoft.backend.domain.User;
import com.whitesoft.backend.user.UserRoleEnum;
import com.whitesoft.backend.user.UserService;
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
