package integration.com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.user.UserService;
import integration.com.whitesoft.pinmap.BaseIntegrationTest;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 */
public class UserServiceIT extends BaseIntegrationTest {

    @Autowired
    protected UserService userService;

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
        softAssertions.assertThat(tzarivan.getLogin()).isEqualTo(login);
        softAssertions.assertThat(tzarivan.getPassword()).isEqualTo(password);
        softAssertions.assertAll();
    }

    @Test
    public void getUserWithNullResult() {

        // Act
        User qwerty = userService.getUser("qwerty");

        // Assert
        assertThat(qwerty).isNull();
    }
}
