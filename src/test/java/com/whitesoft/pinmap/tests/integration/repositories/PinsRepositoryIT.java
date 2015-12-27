package com.whitesoft.pinmap.tests.integration.repositories;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.repositories.UsersRepository;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.*;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Integration tests for {@link PinsRepository}. Obviously, there is no
 * need in this tests, but I have a lack of experience in MongoDB, so having them
 * is not a bad idea, right?
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
    protected User testUser;

    @Before
    public void setup(){

        testUser = usersRepository.insert(getValidTestUser());
    }

    @After
    public void tearDown(){

        pinsRepository.deleteByUser(testUser);
        usersRepository.delete(testUser);
    }

    /**
     * Tests correct insertion of a new pin for a user
     */
    @Test
    public void insert() {

        // Arrange
        String testDescription = "testPin";
        GeoJsonPoint testLocation = getRandomLocation();

        Pin pinToInsert = new Pin();
        pinToInsert.setDescription(testDescription);
        pinToInsert.setLocation(testLocation);
        pinToInsert.setUser(testUser);
        pinToInsert.setCreated(new Date());

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

    /**
     * Tests corrects insertion oа bunch of pins
     */
    @Test
    public void insertBulk(){

        // Arrange
        int pinsCount = 5;
        String testDescription = "testPin";
        List<Pin> pinsToInsert = new ArrayList<>();
        for(int i = 0; i < pinsCount; i++){
            GeoJsonPoint testLocation = getRandomLocation();
            Pin pinToInsert = new Pin();
            pinToInsert.setDescription(testDescription + i);
            pinToInsert.setLocation(testLocation);
            pinToInsert.setUser(testUser);
            pinToInsert.setCreated(new Date());

            pinsToInsert.add(pinToInsert);
        }

        // Act
        List<Pin> insertedPins = pinsRepository.insert(pinsToInsert);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(insertedPins).isNotNull();
        softAssertions.assertThat(insertedPins.size()).isEqualTo(pinsCount);
        softAssertions.assertAll();
    }

    /**
     * Tests correct retrievement of pins by user
     */
    @Test
    public void findByUser(){

        // Arrange
        User tzarivan = usersRepository.findByLogin(getCorrectLogin());

        // Act
        List<Pin> pins = pinsRepository.findByUser(tzarivan);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(pins).isNotNull();
        softAssertions.assertThat(pins.size()).isEqualTo(5);
        softAssertions.assertThat(pins)
                .filteredOn(pin -> pin.getUser().getId().equals(tzarivan.getId()))
                .containsAll(pins);
        softAssertions.assertAll();
    }
}
