package com.whitesoft.pinmap.controllers.advice;

import com.whitesoft.pinmap.dto.ErrorDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by borisbondarenko on 04.01.16.
 *
 * A processor to convert exceptions of application to JSON
 *
 * @author brzzbr
 */
@ControllerAdvice("com.whitesoft.pinmap")
public class RestExceptionProcessor {

    @ExceptionHandler(BadCredentialsException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(value= HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorDTO handleUnauthorizedException(HttpServletRequest req, Throwable ex) {

        return new ErrorDTO(ex.getClass().getCanonicalName(), "","Access denied!!");
    }

    /**
     * Let it be just one handler for all the exceptions for a while.
     * @param req a request object
     * @param ex exception that have been thrown
     * @return error DTO to be consumed on client-side
     */
    @ExceptionHandler(Throwable.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO handleAllTheOtherExceptions(HttpServletRequest req, Throwable ex) {

        String stackTrace = ExceptionUtils.getStackTrace(ex);
        return new ErrorDTO(
                ex.getClass().getCanonicalName(),
                stackTrace,
                "Something bad has happened!");
    }
}
