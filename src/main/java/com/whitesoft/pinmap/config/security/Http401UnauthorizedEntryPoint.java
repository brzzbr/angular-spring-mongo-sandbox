package com.whitesoft.pinmap.config.security;

import com.google.gson.Gson;
import com.whitesoft.pinmap.config.serialization.GSONFactory;
import com.whitesoft.pinmap.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FROM JHIPSTER Generator. Thank you guys!
 * <p>
 * Returns a 401 error code (Unauthorized) to the client.
 */
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private Gson gson = GSONFactory.getGson();

    private final Logger log = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
        throws IOException,
            ServletException {

        log.debug("Pre-authenticated entry point called. Rejecting access");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorDTO err = new ErrorDTO(BadCredentialsException.class.getCanonicalName(), "", "Alarm! Access Denied!");
        String jsonErr = gson.toJson(err);
        response.getOutputStream().println(jsonErr);

    }
}
