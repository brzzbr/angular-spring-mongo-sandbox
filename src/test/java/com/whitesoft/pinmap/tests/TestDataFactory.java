package com.whitesoft.pinmap.tests;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by borisbondarenko on 21.12.15.
 *
 * Factory class for test data.
 *
 * @author brzzbr
 */
public final class TestDataFactory {

    private static double rangeMinX = -180.0;
    private static double rangeMaxX = 180.0;

    private static double rangeMinY = -90.0;
    private static double rangeMaxY = 90.0;

    public static String getTestApiUrl(){
        return "http://localhost:7788/api";
    }

    public static User getValidTestUser(){

        User result = new User();
        result.setEmail("test@test.test");
        result.setFirstName("testFirstName");
        result.setLastName("testLastName");
        result.setLogin("testLogin");
        result.setPassword("827ccb0eea8a706c4c34a16891f84e7b");

        return result;
    }

    public static List<Pin> getTestPins(){

        int count = 5;
        User user = getValidTestUser();
        List<Pin> result = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            Pin pin = new Pin();
            pin.setDescription("testDescription");
            pin.setUser(user);
            pin.setLocation(getRandomLocation());
            pin.setCreated(new Date());
            pin.setName("testName" + 1);
            result.add(pin);
        }

        return result;
    }

    public static String getCorrectLogin(){

        return "tzarivan";
    }

    public static String getWrongLogin(){

        return "qwerty";
    }

    public static String getCorrectPassword(){

        return "12345";
    }

    public static String getWronPassword(){

        return "11111";
    }

    public static GeoJsonPoint getRandomLocation(){

        Random r = new Random();

        double x = rangeMinX + (rangeMaxX - rangeMinX) * r.nextDouble();
        double y = rangeMinY + (rangeMaxY - rangeMinY) * r.nextDouble();

        return new GeoJsonPoint(x, y);
    }
}
