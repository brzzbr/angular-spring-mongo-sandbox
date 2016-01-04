package com.whitesoft.pinmap.tests.unit.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.PinServiceImpl;
import com.whitesoft.pinmap.services.SubService;
import com.whitesoft.pinmap.services.exceptions.InvalidPinException;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;

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
    protected PinsRepository pinsRepository;

    protected SubService subService;

    @Before
    public void setup() {

        pinsRepository = Mockito.mock(PinsRepository.class);
        ReflectionTestUtils.setField(pinService, "pinsRepository", pinsRepository);

        subService = Mockito.mock(SubService.class);
        ReflectionTestUtils.setField(pinService, "subService", subService);
    }

    /**
     * Tests correct retrieval of current user's pins.
     * User doesn't have any subscriptions.
     * Get all the pins for all the time.
     * Trivial method leads to trivial test=)
     */
    @Test
    public void getMyPinsWithoutSubscriptionsForAllTheTime() {

        // Arrange
        User user = getValidTestUser();
        List<Pin> pins = getTestPins(user, 5);
        Mockito.when(pinsRepository.findByUserIn(Collections.singletonList(user))).thenReturn(pins);
        Mockito.when(subService.getSubs(user)).thenReturn(Collections.emptyList());

        // Act
        List<Pin> myPins = pinService.getPins(user);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myPins).isNotNull();
        softAssertions.assertThat(myPins.size()).isEqualTo(5);
        softAssertions.assertThat(myPins).containsAll(pins);
        softAssertions.assertAll();
    }

    /**
     * Tests correct retrieval of current user's pins.
     * User has a subscriptions.
     * Get all the pins for all the time.
     */
    @Test
    public void getMyPinsWithSubscriptionsForAllTheTime() {

        // Arrange
        User subscriber = getValidTestUser();
        User authorUser = getValidTestUser_2();
        Sub sub = getValidSub(authorUser, subscriber);
        List<Pin> pins = getTestPins(subscriber, 10);
        pins.addAll(getTestPins(authorUser, 5));
        Mockito.when(pinsRepository.findByUserIn(argThat(

                new ArgumentMatcher<List<User>>() {
                    @Override
                    public boolean matches(Object argument) {

                        List<User> arg = (List<User>) argument;
                        return arg.contains(subscriber) && arg.contains(authorUser);
                    }
                }

        ))).thenReturn(pins);
        Mockito.when(subService.getSubs(subscriber)).thenReturn(Collections.singletonList(sub));

        // Act
        List<Pin> myPins = pinService.getPins(subscriber);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myPins).isNotNull();
        softAssertions.assertThat(myPins.size()).isEqualTo(15);
        softAssertions.assertThat(myPins).containsAll(pins);
        softAssertions.assertThat(myPins.stream()
                .filter(pin -> pin.getUser().getUsername().equals(subscriber.getUsername()))
                .count()).isEqualTo(10);
        softAssertions.assertThat(myPins.stream()
                .filter(pin -> pin.getUser().getUsername().equals(authorUser.getUsername()))
                .count()).isEqualTo(5);
        softAssertions.assertAll();
    }

    /**
     * Tests correct retrieval of current user's pins.
     * User has a subscriptions.
     * Get all the pins for some period of time.
     */
    @Test
    public void getMyPinsWithSubscriptionsForPeriodOfTime() {

        // Arrange
        User subscriber = getValidTestUser();
        User authorUser = getValidTestUser_2();
        Sub sub = getValidSub(authorUser, subscriber);
        List<Pin> pins = getTestPins(subscriber, 10);
        pins.addAll(getTestPins(authorUser, 5));
        Mockito.when(pinsRepository.findByUserInAndCreatedGreaterThan(argThat(

                new ArgumentMatcher<List<User>>() {
                    @Override
                    public boolean matches(Object argument) {

                        List<User> arg = (List<User>) argument;
                        return arg.contains(subscriber) && arg.contains(authorUser);
                    }
                }

        ), any())).thenReturn(pins);
        Mockito.when(subService.getSubs(subscriber)).thenReturn(Collections.singletonList(sub));

        // Act
        List<Pin> myPins = pinService.getPins(subscriber, new Date());

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(myPins).isNotNull();
        softAssertions.assertThat(myPins.size()).isEqualTo(15);
        softAssertions.assertThat(myPins).containsAll(pins);
        softAssertions.assertThat(myPins.stream()
                .filter(pin -> pin.getUser().getUsername().equals(subscriber.getUsername()))
                .count()).isEqualTo(10);
        softAssertions.assertThat(myPins.stream()
                .filter(pin -> pin.getUser().getUsername().equals(authorUser.getUsername()))
                .count()).isEqualTo(5);
        softAssertions.assertAll();
    }

    /**
     * Tests correct scenario of adding new pin.
     */
    @Test
    public void addPin() {

        // Arrange
        User user = getValidTestUser();
        Pin insertedPin = getTestPins(user, 5).get(0);
        Pin pinToInsert = new Pin();
        pinToInsert.setName(insertedPin.getName());
        pinToInsert.setDescription(insertedPin.getDescription());
        pinToInsert.setLocation(insertedPin.getLocation());
        Mockito.when(pinsRepository.insert(pinToInsert)).thenReturn(insertedPin);

        // Act
        Pin returnedPin = pinService.addMyPin(user, pinToInsert);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(returnedPin).isNotNull();
        softAssertions.assertThat(returnedPin.getName()).isEqualTo(insertedPin.getName());
        softAssertions.assertThat(returnedPin.getDescription()).isEqualTo(insertedPin.getDescription());
        softAssertions.assertThat(returnedPin.getUser().getUsername()).isEqualTo(insertedPin.getUser().getUsername());
        softAssertions.assertThat(returnedPin.getUsername()).isEqualTo(user.getUsername());
        softAssertions.assertThat(returnedPin.getLocation().getX()).isEqualTo(insertedPin.getLocation().getX());
        softAssertions.assertThat(returnedPin.getLocation().getY()).isEqualTo(insertedPin.getLocation().getY());
        softAssertions.assertAll();
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_1() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(500, 100));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_2() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(-190, 0));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_3() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(190, 0));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_4() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(0, -100));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_5() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(0, 100));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attempt to add a pin with incorrect location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinIncorrectCoordinates_6() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();
        pinToInsert.setLocation(new GeoJsonPoint(-500, -100));

        // Act
        pinService.addMyPin(user, pinToInsert);
    }

    /**
     * Tests an attemt to add a pin with null location
     */
    @Test(expected = InvalidPinException.class)
    public void addPinNullCoordinates() {

        // Arrange
        User user = getValidTestUser();
        Pin pinToInsert = new Pin();

        // Act
        pinService.addMyPin(user, pinToInsert);
    }
}
