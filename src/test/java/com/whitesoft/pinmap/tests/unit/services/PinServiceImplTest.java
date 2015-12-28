package com.whitesoft.pinmap.tests.unit.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.PinServiceImpl;
import com.whitesoft.pinmap.services.UserService;
import com.whitesoft.pinmap.services.exceptions.InvalidPinException;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.getTestPins;
import static com.whitesoft.pinmap.tests.TestDataFactory.getValidTestUser;

/**
 * Created by borisbondarenko on 24.12.15.
 * <p/>
 * Unit tests for {@link PinServiceImpl}
 *
 * @author brzzbr
 */
public class PinServiceImplTest {

    // class to test
    protected PinServiceImpl pinService = new PinServiceImpl();

    // dependencies
    protected UserService userService;

    protected PinsRepository pinsRepository;

    @Before
    public void setup() {

        userService = Mockito.mock(UserService.class);
        pinsRepository = Mockito.mock(PinsRepository.class);
        ReflectionTestUtils.setField(pinService, "userService", userService);
        ReflectionTestUtils.setField(pinService, "pinsRepository", pinsRepository);
    }

    /**
     * Tests correct retrieval of current user's pins.
     * Trivial method leads to trivial test=)
     */
    @Test
    public void getMyPins() {

        // Arrange
        User user = getValidTestUser();
        List<Pin> pins = getTestPins();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(pinsRepository.findByUser(user)).thenReturn(pins);

        // Act
        List<Pin> myPins = pinService.getMyPins();

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myPins).isNotNull();
        softAssertions.assertThat(myPins.size()).isEqualTo(5);
        softAssertions.assertThat(myPins)
                .containsAll(pins);
        softAssertions.assertAll();
    }

    /**
     * Tests correct scenario of adding new pin.
     */
    @Test
    public void addPin() {

        // Arrange
        User user = getValidTestUser();
        Pin insertedPin = getTestPins().get(0);
        Pin pinToInsert = new Pin();
        pinToInsert.setName(insertedPin.getName());
        pinToInsert.setDescription(insertedPin.getDescription());
        pinToInsert.setLocation(insertedPin.getLocation());
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(pinsRepository.insert(pinToInsert)).thenReturn(insertedPin);

        // Act
        Pin returnedPin = pinService.addMyPin(pinToInsert);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(returnedPin).isNotNull();
        softAssertions.assertThat(returnedPin.getName()).isEqualTo(insertedPin.getName());
        softAssertions.assertThat(returnedPin.getDescription()).isEqualTo(insertedPin.getDescription());
        softAssertions.assertThat(returnedPin.getUser().getUsername()).isEqualTo(insertedPin.getUser().getUsername());
        softAssertions.assertThat(returnedPin.getLocation().getX()).isEqualTo(insertedPin.getLocation().getX());
        softAssertions.assertThat(returnedPin.getLocation().getY()).isEqualTo(insertedPin.getLocation().getY());
        softAssertions.assertAll();
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates() {

        // Arrange
        User user = getValidTestUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(500, 100));

        // Act
        pinService.addMyPin(pinToInsert);
    }

    /**
     * Tests an attemt to add a pin with null location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinNullCoordinates() {

        // Arrange
        User user = getValidTestUser();
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Pin pinToInsert = new Pin();

        // Act
        pinService.addMyPin(pinToInsert);
    }
}
