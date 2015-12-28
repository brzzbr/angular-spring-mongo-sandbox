package com.whitesoft.pinmap.tests.integration.api;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.whitesoft.pinmap.config.serialization.GSONFactory;
import com.whitesoft.pinmap.controllers.PinController;
import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.dto.PinDTO;
import com.whitesoft.pinmap.dto.CollectionDTO;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.UserService;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

import static com.whitesoft.pinmap.tests.TestDataFactory.getCorrectLogin;
import static com.whitesoft.pinmap.tests.TestDataFactory.getTestApiUrl;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by borisbondarenko on 24.12.15.
 * <p/>
 * Integration tests for {@link PinController}
 *
 * @author brzzbr
 */
public class PinControllerIT extends BaseIntegrationTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PinsRepository pinsRepository;

    /**
     * Gets pins for tzarivan user
     *
     * @throws UnirestException
     */
    @Test
    public void getMyPins() throws UnirestException {

        // Arrange
        User currentUser = userService.getCurrentUser();
        List<Pin> pinsFromBase = pinsRepository.findByUser(currentUser);

        // Act
        HttpResponse<String> response = setAuthHeader(Unirest.get(getTestApiUrl() + "/pins")).asString();

        // Assert
        CollectionDTO pins = gson.fromJson(response.getBody(), CollectionDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(pins).isNotNull();
        softAssertions.assertThat(pins.getItems().size()).isEqualTo(pinsFromBase.size());
        softAssertions.assertAll();
    }

    /**
     * Throws an 401 Unauthorized status on attempt to get pins without a token
     *
     * @throws UnirestException
     */
    @Test
    public void getMyPinsUnauthorized() throws UnirestException {

        // Act
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get(getTestApiUrl() + "/pins").asJson();

        // Assert
        assertThat(jsonNodeHttpResponse.getStatus()).isEqualTo(SC_UNAUTHORIZED);
    }

    @Test
    public void addPin() throws UnirestException {

        // Arrange
        Pin pinToAdd = new Pin();
        pinToAdd.setLocation(new GeoJsonPoint(10, 20));
        pinToAdd.setName("name");
        pinToAdd.setDescription("description");
        Gson gson = GSONFactory.getGson();
        String jsonPin = gson.toJson(pinToAdd);

        // Act
        HttpResponse<String> response = setAuthHeader(Unirest.post(getTestApiUrl() + "/pins"))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(jsonPin)
                .asString();

        // Assert
        PinDTO insertedPin = gson.fromJson(response.getBody(), PinDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(insertedPin).isNotNull();
        softAssertions.assertThat(insertedPin.getUsername()).isEqualTo(getCorrectLogin());
        softAssertions.assertThat(insertedPin.getName()).isEqualTo(pinToAdd.getName());
        softAssertions.assertThat(insertedPin.getDescription()).isEqualTo(pinToAdd.getDescription());
        softAssertions.assertThat(insertedPin.getLocation().getX()).isEqualTo(pinToAdd.getLocation().getX());
        softAssertions.assertThat(insertedPin.getLocation().getY()).isEqualTo(pinToAdd.getLocation().getY());
        softAssertions.assertAll();
    }

    /**
     * Tests an unauthorized attempt to add a new pin
     * @throws UnirestException
     */
    @Test
    public void addPinUnauthorized() throws UnirestException {

        // Arrange
        Pin pinToAdd = new Pin();
        pinToAdd.setLocation(new GeoJsonPoint(0, 0));
        pinToAdd.setName("name");
        pinToAdd.setDescription("description");
        String jsonPin = gson.toJson(pinToAdd);

        // Act
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.post(getTestApiUrl() + "/pins").body(jsonPin).asJson();

        // Assert
        assertThat(jsonNodeHttpResponse.getStatus()).isEqualTo(SC_UNAUTHORIZED);
    }
}
