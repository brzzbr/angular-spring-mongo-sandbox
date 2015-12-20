package integration.com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.config.AppConfiguration;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class UsersRepositoryIT {

    @Autowired
    protected UsersRepository usersRepository;

    @Test
    public void findByLoginCorrect() {

        // Act
        User tzarivan = usersRepository.findByLogin("tzarivan");

        // Assert
        assertThat(tzarivan).isNotNull();
    }

    @Test
    public void findByLoginNull() {

        // Act
        User tzarivan = usersRepository.findByLogin("qwerty");

        // Assert
        assertThat(tzarivan).isNull();
    }
}
