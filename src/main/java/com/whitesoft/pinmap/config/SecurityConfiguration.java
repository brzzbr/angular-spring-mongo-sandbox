package com.whitesoft.pinmap.config;

import com.whitesoft.pinmap.config.security.*;
import com.whitesoft.pinmap.config.security.xauth.TokenProvider;
import com.whitesoft.pinmap.config.security.xauth.XAuthTokenConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Configuration for a Spring Security. Here we have a token-based
 * authentication with a custom authentication entry point {@link Http401UnauthorizedEntryPoint}.
 * <p>
 * The heart of authentication mechanism is {@link TokenProvider} providing a tokens for authenticated
 * user and {@link com.whitesoft.pinmap.config.security.xauth.XAuthTokenFilter}, which is injected in a chain of filters.
 *
 * @author brzzbr
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new Md5PasswordEncoder());
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/js/**/*.js")
                .antMatchers("/fonts/**/*.*")
                .antMatchers("/css/**/*.css");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .csrf()
                    .disable()
                    .headers()
                    .frameOptions()
                    .disable()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/authenticate").permitAll()
//                    .antMatchers("/api/activate").permitAll()
//                    .antMatchers("/api/authenticate").permitAll()
//                    .antMatchers("/api/account/reset_password/init").permitAll()
//                    .antMatchers("/api/account/reset_password/finish").permitAll()
//                    .antMatchers("/api/logs/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/api/audits/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/api/**").authenticated()
//                    .antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/health/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/configprops/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/mappings/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/liquibase/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/v2/api-docs/**").permitAll()
//                    .antMatchers("/configuration/security").permitAll()
//                    .antMatchers("/configuration/ui").permitAll()
//                    .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
//                    .antMatchers("/protected/**").authenticated()
                .and()
                    .apply(securityConfigurerAdapter());

    }

    private XAuthTokenConfigurer securityConfigurerAdapter() {
        return new XAuthTokenConfigurer(userDetailsService, tokenProvider);
    }
}