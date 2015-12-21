package unit.com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import com.whitesoft.pinmap.user.UserServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import unit.com.whitesoft.pinmap.TestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 */
public class UserServiceImplTest {

    // class to test
    protected UserServiceImpl userService = new UserServiceImpl();

    // dependencies
    protected UsersRepository usersRepository;

    @Before
    public void setup() {

        usersRepository = Mockito.mock(UsersRepository.class);
        ReflectionTestUtils.setField(userService, "usersRepository", usersRepository);
    }

    @Test
    public void getUser() {

        // Assert
        User testUser = TestDataFactory.getValidTestUser();
        Mockito.when(usersRepository.findByLogin(testUser.getLogin())).thenReturn(testUser);

        // Act
        User user = userService.getUser(testUser.getLogin());

        // Arrange
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user).isNotNull();
        softAssertions.assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
        softAssertions.assertThat(user.getFirstName()).isEqualTo(testUser.getFirstName());
        softAssertions.assertThat(user.getLastName()).isEqualTo(testUser.getLastName());
        softAssertions.assertThat(user.getLogin()).isEqualTo(testUser.getLogin());
        softAssertions.assertThat(user.getPassword()).isEqualTo(testUser.getPassword());
        softAssertions.assertAll();
    }

    @Test
    public void getUserWithNullResult() {

        // Assert
        String testLogin = "testLogin";
        Mockito.when(usersRepository.findByLogin(testLogin)).thenReturn(null);

        // Act
        User user = userService.getUser(testLogin);

        // Arrange
        assertThat(user).isNull();
    }

}
