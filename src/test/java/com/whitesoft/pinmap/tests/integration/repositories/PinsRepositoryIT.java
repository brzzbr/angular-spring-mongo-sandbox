package com.whitesoft.pinmap.tests.integration.repositories;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.repositories.UsersRepository;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import static com.whitesoft.pinmap.tests.TestDataFactory.getCorrectLogin;
import static com.whitesoft.pinmap.tests.TestDataFactory.getRandomLocation;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Integration tests for {@link PinsRepository}
 *
 * @author brzzbr
 */
public class PinsRepositoryIT extends BaseIntegrationTest {

    // class to test
    @Autowired
    protected PinsRepository pinsRepository;

    // dependencies
    @Autowired
    protected UsersRepository usersRepository;

    /**
     * Tests correct insertion of a new pin for a user
     */
    @Test
    public void insert() {

        // Arrange
        String testDescription = "testPin";
        GeoJsonPoint testLocation = getRandomLocation();
        User testUser = usersRepository.findByLogin(getCorrectLogin());

        Pin pinToInsert = new Pin();
        pinToInsert.setDescription(testDescription);
        pinToInsert.setLocation(testLocation);
        pinToInsert.setUser(testUser);

        // Act
        Pin insertedPin = pinsRepository.insert(pinToInsert);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(insertedPin).isNotNull();
        softAssertions.assertThat(insertedPin.getDescription()).isEqualTo(testDescription);
        softAssertions.assertThat(insertedPin.getLocation()).isEqualTo(testLocation);
        softAssertions.assertThat(insertedPin.getUser().getId()).isEqualTo(testUser.getId());
        softAssertions.assertAll();
    }
}
