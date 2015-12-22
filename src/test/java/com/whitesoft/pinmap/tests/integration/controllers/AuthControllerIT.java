package com.whitesoft.pinmap.tests.integration.controllers;

import com.whitesoft.pinmap.controllers.AuthController;
import com.whitesoft.pinmap.config.security.xauth.Token;
import com.whitesoft.pinmap.tests.TestDataFactory;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Integration tests for {@link AuthController}.
 *
 * @author brzzbr
 */
public class AuthControllerIT extends BaseIntegrationTest{

    // class to test
    @Autowired
    protected AuthController authController;

    /**
     * Tests successful authentication.
     */
    @Test
    public void getCurrentUser(){

        // Act
        Token tzarivan = authController.authorize(TestDataFactory.getCorrectLogin(), TestDataFactory.getCorrectPassword());

        // Arrange
        assertThat(tzarivan).isNotNull();
    }

    /**
     * Tests failed authentication with incorrect password.
     */
    @Test(expected = BadCredentialsException.class)
    public void getCurrentUserWithWrongPassword(){

        // Act
        authController.authorize(TestDataFactory.getCorrectLogin(), TestDataFactory.getWronPassword());
    }

}
