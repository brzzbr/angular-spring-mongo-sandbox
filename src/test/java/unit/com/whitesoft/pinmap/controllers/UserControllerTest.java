package unit.com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.controllers.UserController;
import org.junit.Test;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by borisbondarenko on 21.12.15.
 */
public class UserControllerTest {

    // class to test
    protected UserController userController = new UserController();

//    @Test
//    public void getCurrentUser(){
//
//        // Assert
//        Principal testPrincipal = new Principal() {
//            @Override
//            public String getName() {
//                return "principal name";
//            }
//        };
//
//        // Act
//        String currentUser = userController.isAuthenticated(testPrincipal);
//
//        // Arrange
//        assertThat(currentUser).isEqualTo(testPrincipal);
//    }
//
//    @Test
//    public void getCurrentUserWithNullResult(){
//
//        // Act
//        Principal currentUser = userController.isAuthenticated(null);
//
//        // Arrange
//        assertThat(currentUser).isNull();
//    }

}
