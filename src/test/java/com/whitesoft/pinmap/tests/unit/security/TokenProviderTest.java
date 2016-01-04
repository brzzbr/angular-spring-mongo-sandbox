package com.whitesoft.pinmap.tests.unit.security;

import com.whitesoft.pinmap.config.security.xauth.Token;
import com.whitesoft.pinmap.config.security.xauth.TokenProvider;
import com.whitesoft.pinmap.config.security.UserRoleEnum;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 22.12.15.
 *
 * Unit tests for {@link TokenProvider}.
 */
public class TokenProviderTest {

    // class to test
    protected TokenProvider tokenProvider = new TokenProvider(secret, expired);

    // dependencies
    protected static String secret = "testSecret";
    protected static int expired = 100;

    // assertion objects
    protected String testUser = "testUser";
    protected String testPassword = "testPassword";
    protected String testAuthToken = testUser + ":azaza:expires";

    /**
     * Get the username from token
     */
    @Test
    public void getUserNameFromToken(){

        // Act
        String userNameFromToken = tokenProvider.getUserNameFromToken(testAuthToken);

        // Arrange
        assertThat(userNameFromToken).isEqualTo(testUser);
    }

    /**
     * Tests correct token creation for test credentials
     */
    @Test
    public void createToken(){

        // Act
        Token token = tokenProvider.createToken(
                new User(testUser, testPassword, Collections.singleton(new SimpleGrantedAuthority(UserRoleEnum.USER.name()))));

        // Assert
        assertThat(token).isNotNull();
        //assertThat(token.getToken()).isEqualTo(testToken.getToken());
        //assertThat(token.getExpires()).isEqualTo(testToken.getExpires());
    }
}
