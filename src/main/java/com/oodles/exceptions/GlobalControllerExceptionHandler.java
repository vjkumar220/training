package com.oodles.exceptions;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> constraintViolationException(ConstraintViolationException ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null,ex);
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object>  noHandlerFoundException(Exception ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, ex);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object>  unknownException(Exception ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, ex);
    }
    @ExceptionHandler(value = { NoSuchElementException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object>  noSuchElementException(Exception ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, ex);
    }
    @ExceptionHandler(value = { NullPointerException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object>  nullPointerException(Exception ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, ex);
    }
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object>  noresourceFoundException(Exception ex) {
        LOG.error(ex.getCause().toString());
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Id does not exist", null, ex);
    }

    
    
    
}