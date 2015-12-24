package com.whitesoft.pinmap.tests.integration;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.whitesoft.pinmap.config.AppConfiguration;
import com.whitesoft.pinmap.tests.TestGsonFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.whitesoft.pinmap.tests.TestDataFactory.*;

/**
 * Created by borisbondarenko on 21.12.15.
 * <p/>
 * Class of base integration test. Injects spring context.
 *
 * @author brzzbr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public abstract class BaseIntegrationTest {

    private String token;

    protected static Gson gson = TestGsonFactory.getGson();

    @Before
    public void loginTestUser() throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.post(getTestApiUrl() + "/authenticate")
                .field("username", getCorrectLogin())
                .field("password", getCorrectPassword())
                .asJson();

        this.token = response.getBody().getObject().get("token").toString();
    }

    @After
    public void logout() {

        token = null;
    }

    protected <T extends HttpRequest> HttpRequest setAuthHeader(T request) {

        return request.header("x-auth-token", token);
    }
}
