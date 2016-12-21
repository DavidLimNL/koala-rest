package com.koala.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionAttributes {
	/**
     * The timestamp attribute key.
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * The status attribute key.
     */
    public static final String STATUS = "status";
    /**
     * The error attribute key.
     */
    public static final String ERROR = "error";
    /**
     * The exception attribute key.
     */
    public static final String EXCEPTION = "exception";
    /**
     * The message attribute key.
     */
    public static final String MESSAGE = "message";
    /**
     * The path attribute key.
     */
    public static final String PATH = "path";

    /**
     * Returns a {@link Map} of exception attributes. The Map may be used to
     * display an error page or serialized into a {@link ResponseBody}.
     * 
     * @param exception the exception reported.
     * @param httpRequest the HttpServletRequest in which the exception
     *        occurred.
     * @param httpStatus the HttpStatus value that will be used in the
     *        {@link HttpServletResponse}.
     * @return a Map of exception attributes.
     */
    public Map<String, Object> getExceptionAttributes(Exception exception,
            HttpServletRequest httpRequest, HttpStatus httpStatus) {

        Map<String, Object> exceptionAttributes = new LinkedHashMap<String, Object>();

        exceptionAttributes.put(TIMESTAMP, new Date());
        exceptionAttributes.put(STATUS, httpStatus.value());
        exceptionAttributes.put(ERROR, httpStatus.getReasonPhrase());
        exceptionAttributes.put(EXCEPTION, exception.getClass().getName());
        exceptionAttributes.put(MESSAGE, exception.getMessage());
        exceptionAttributes.put(PATH, httpRequest.getServletPath());

        return exceptionAttributes;
    }

}
