package integration.com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import integration.com.whitesoft.pinmap.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Integration tests for {@link UsersRepository}.
 *
 * @author brzzbr
 */
public class UsersRepositoryIT extends BaseIntegrationTest{

    // class to test
    @Autowired
    protected UsersRepository usersRepository;

    /**
     * Tests user's retrievement by login
     */
    @Test
    public void findByLogin() {

        // Act
        User tzarivan = usersRepository.findByLogin("tzarivan");

        // Assert
        assertThat(tzarivan).isNotNull();
    }

    /**
     * Tests that there is no user with the login in a repo
     */
    @Test
    public void findByLoginWithNullResult() {

        // Act
        User tzarivan = usersRepository.findByLogin("qwerty");

        // Assert
        assertThat(tzarivan).isNull();
    }


}
