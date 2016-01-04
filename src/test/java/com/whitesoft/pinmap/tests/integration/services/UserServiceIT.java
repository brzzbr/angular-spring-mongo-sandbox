package com.whitesoft.pinmap.tests.integration.services;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.services.UserService;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Integration tests fo {@link UserService}.
 *
 * @author brzzbr
 */
public class UserServiceIT extends BaseIntegrationTest {

    // class to test
    @Autowired
    protected UserService userService;

    /**
     * Tests successful user retrievement with correct credentials
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void getUser() throws NoSuchAlgorithmException {

        // Arrange
        String login = "tzarivan";

        String stringPassword="12345";
        String password = DigestUtils.md5Hex(stringPassword);

        // Act
        User tzarivan = userService.getUser(login);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tzarivan).isNotNull();
        softAssertions.assertThat(tzarivan.getUsername()).isEqualTo(login);
        softAssertions.assertThat(tzarivan.getPassword()).isEqualTo(password);
        softAssertions.assertAll();
    }

    /**
     * Tests null result of gettin invalid user
     */
    @Test
    public void getUserWithNullResult() {

        // Act
        User qwerty = userService.getUser("qwerty");

        // Assert
        assertThat(qwerty).isNull();
    }
}
