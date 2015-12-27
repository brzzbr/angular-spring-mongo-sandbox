package com.whitesoft.pinmap.tests.integration.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.whitesoft.pinmap.controllers.PinController;
import com.whitesoft.pinmap.dto.PinsCollectionDTO;
import com.whitesoft.pinmap.tests.integration.BaseIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static com.whitesoft.pinmap.tests.TestDataFactory.getTestApiUrl;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by borisbondarenko on 24.12.15.
 *
 * Integration tests for {@link PinController}
 *
 * @author brzzbr
 */
public class PinControllerIT extends BaseIntegrationTest {

    /**
     * Gets pins for tzarivan user
     * @throws UnirestException
     */
    @Test
    public void getMyPins() throws UnirestException {

        // Act
        HttpResponse<String> response = setAuthHeader(Unirest.get(getTestApiUrl() + "/mypins")).asString();

        // Assert
        PinsCollectionDTO pins = gson.fromJson(response.getBody(), PinsCollectionDTO.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(pins).isNotNull();
        softAssertions.assertThat(pins.getItems().size()).isEqualTo(5);
        softAssertions.assertThat(pins.getItems())
                .filteredOn(pin -> pin.getUserName().equals("me"))
                .containsAll(pins.getItems());
        softAssertions.assertAll();
    }

    /**
     * Throws an 401 Unauthorized status on attempt to get pins without a token
     * @throws UnirestException
     */
    @Test
    public void getMyPinsUnauthorized() throws UnirestException {

        // Act
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get(getTestApiUrl() + "/mypins").asJson();

        // Assert
        assertThat(jsonNodeHttpResponse.getStatus()).isEqualTo(SC_UNAUTHORIZED);
    }
}
