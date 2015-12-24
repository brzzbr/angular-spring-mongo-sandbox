package com.whitesoft.pinmap.tests.unit.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.PinServiceImpl;
import com.whitesoft.pinmap.services.UserService;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.getTestPins;
import static com.whitesoft.pinmap.tests.TestDataFactory.getValidTestUser;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 24.12.15.
 *
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
    public void getMyPins(){

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
                .filteredOn(pin -> pin.getUser().equals(user))
                .containsAll(myPins);
        softAssertions.assertAll();
    }
}
