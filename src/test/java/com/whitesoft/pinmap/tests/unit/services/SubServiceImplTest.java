package com.whitesoft.pinmap.tests.unit.services;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.SubsRepository;
import com.whitesoft.pinmap.services.SubServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

/**
 * Created by borisbondarenko on 04.01.16.
 *
 * Unit-tests for testing {@link SubServiceImpl}
 *
 * @author brzzbr
 */
public class SubServiceImplTest {

    // class to test
    protected SubServiceImpl subService = new SubServiceImpl();

    // dependencies
    protected SubsRepository subsRepository;

    @Before
    public void setup() {

        subsRepository = Mockito.mock(SubsRepository.class);
        ReflectionTestUtils.setField(subService, "subsRepository", subsRepository);
    }

    /**
     * Gets subscriptions for user who neve subscribes on anyone.
     */
    @Test
    public void getSubsEmpty(){

        // Arrange
        User subscriber = getValidTestUser();

        // Act
        List<Sub> subs = subService.getSubs(subscriber);

        // Assert
        assertThat(subs).isNotNull();
        assertThat(subs).isEmpty();
    }

    /**
     * Gets subs for user
     */
    @Test
    public void getSubs(){

        // Arrange
        User subscriber = getValidTestUser();
        User author = getValidTestUser_2();
        Sub mockSub = getValidSub(author, subscriber);
        Mockito.when(subsRepository.findBySubscriber(subscriber)).thenReturn(Collections.singletonList(mockSub));

        // Act
        List<Sub> subs = subService.getSubs(subscriber);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(subs).isNotNull();
        softAssertions.assertThat(subs.size()).isEqualTo(1);
        softAssertions.assertThat(subs.get(0).getAuthor().getUsername()).isEqualTo(author.getUsername());
        softAssertions.assertThat(subs.get(0).getSubscriber().getUsername()).isEqualTo(subscriber.getUsername());
        softAssertions.assertAll();
    }

    /**
     * Subscribes on user
     */
    @Test
    public void subscribe(){

        // Arrange
        User subscriber = getValidTestUser();
        User author = getValidTestUser_2();
        Mockito.when(subsRepository.insert(any(Sub.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return args[0];
        });

        // Act
        Sub subscribe = subService.subscribe(subscriber, author);

        // Assert
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(subscribe).isNotNull();
        softAssertions.assertThat(subscribe.getAuthor().getUsername()).isEqualTo(author.getUsername());
        softAssertions.assertThat(subscribe.getSubscriber().getUsername()).isEqualTo(subscriber.getUsername());
        softAssertions.assertThat(subscribe.getSince()).isBefore(new Date());
        softAssertions.assertAll();
    }

    /**
     * Unsubscribes from user. Well... the test is kinda weird.
     */
    @Test
    public void unsubscribe(){

        // Arrange
        User subscriber = getValidTestUser();
        User author = getValidTestUser();
        Sub mockSub = getValidSub(author, subscriber);
        Mockito.when(subsRepository.findBySubscriberAndAuthor(subscriber, author)).thenReturn(mockSub);

        // Act
        subService.unsubscribe(subscriber, author);

        // Assert
    }
}
