package com.whitesoft.pinmap.config;

import com.whitesoft.pinmap.security.*;
import com.whitesoft.pinmap.security.xauth.TokenProvider;
import com.whitesoft.pinmap.security.xauth.XAuthTokenConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.inject.Inject;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Configuration
@EnableWebSecurity
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
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/api/register").permitAll()
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

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
