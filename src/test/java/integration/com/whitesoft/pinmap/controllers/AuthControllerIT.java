package integration.com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.controllers.AuthController;
import com.whitesoft.pinmap.config.security.xauth.Token;
import integration.com.whitesoft.pinmap.BaseIntegrationTest;
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
        Token tzarivan = authController.authorize("tzarivan", "12345");

        // Arrange
        assertThat(tzarivan).isNotNull();
    }

    /**
     * Tests failed authentication with incorrect password.
     */
    @Test(expected = BadCredentialsException.class)
    public void getCurrentUserWithWrongPassword(){

        // Act
        authController.authorize("tzarivan", "11111");
    }

}
