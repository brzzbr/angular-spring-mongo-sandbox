package com.whitesoft.pinmap.tests.integration.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.whitesoft.pinmap.controllers.AuthController;
import com.whitesoft.pinmap.config.security.xauth.Token;
import com.whitesoft.pinmap.tests.TestDataFactory;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletResponse;

import static com.whitesoft.pinmap.tests.TestDataFactory.*;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
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
     * Tests successful authentication direct via controller
     */
    @Test
    public void getCurrentUserDirect(){

        // Act
        Token tzarivan = authController.authorize(TestDataFactory.getCorrectLogin(), TestDataFactory.getCorrectPassword());

        // Arrange
        assertThat(tzarivan).isNotNull();
    }

    /**
     * Tests failed authentication with incorrect password direct via controller
     */
    @Test(expected = BadCredentialsException.class)
    public void getCurrentUserWithWrongPasswordDirect(){

        // Act
        authController.authorize(TestDataFactory.getCorrectLogin(), TestDataFactory.getWronPassword());
    }

    /**
     * Tests successful authentication
     */
    @Test
    public void getCurrentUser() throws UnirestException {

        // Act
        HttpResponse<JsonNode> response = Unirest.post(getTestApiUrl() + "/authenticate")
                .field("username", getCorrectLogin())
                .field("password", getCorrectPassword())
                .asJson();

        // Assert
        String token = response.getBody().getObject().get("token").toString();
        assertThat(token).isNotNull();
    }

    /**
     * Tests failed authentication with incorrect password
     */
    @Test
    public void getCurrentUserWithWrongPassword() throws UnirestException {

        // Act
        HttpResponse<JsonNode> response = Unirest.post(getTestApiUrl() + "/authenticate")
                .field("username", getCorrectLogin())
                .field("password", getWronPassword())
                .asJson();

        // Assert
        assertThat(response.getStatus()).isEqualTo(SC_UNAUTHORIZED);
    }

}
