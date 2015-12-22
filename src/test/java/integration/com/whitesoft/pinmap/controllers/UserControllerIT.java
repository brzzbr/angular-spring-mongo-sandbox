package integration.com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.controllers.UserController;
import com.whitesoft.pinmap.security.xauth.Token;
import integration.com.whitesoft.pinmap.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 */
public class UserControllerIT extends BaseIntegrationTest{

    // class to test
    @Autowired
    protected UserController userController;

    @Test
    public void getCurrentUser(){

        // Act
        Token tzarivan = userController.authorize("tzarivan", "12345");

        // Arrange
        assertThat(tzarivan).isNotNull();
    }

    @Test(expected = BadCredentialsException.class)
    public void getCurrentUserWithWrongPassword(){

        // Act
        userController.authorize("tzarivan", "11111");
    }

}
