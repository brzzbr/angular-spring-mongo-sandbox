package unit.com.whitesoft.pinmap;

import com.whitesoft.pinmap.domain.User;

/**
 * Created by borisbondarenko on 21.12.15.
 */
public final class TestDataFactory {

    public static User getValidTestUser(){

        User result = new User();
        result.setEmail("test@test.test");
        result.setFirstName("testFirstName");
        result.setLastName("testLastName");
        result.setLogin("testLogin");
        result.setPassword("testPassword");

        return result;
    }
}
