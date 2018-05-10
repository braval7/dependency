package com.salesforce.tests.dependency.exception;

/**
 * Runtime exception thrown by application
 *
 * @author Bhargav
 * @since 05/09/2018
 */
public class SfRuntimeException extends Exception {

    public SfRuntimeException() {
    }

    public SfRuntimeException(String message) {
        super(message);
    }

    public SfRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
