package com.shablinskaya.testproject.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Parent for any exception
 */
@NoArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private String errorCode;

    /**
     * Creates Exception with message.
     *
     * @param message message
     */
    public CustomException(final String message) {
        super(message);
    }

    /**
     * Creates Exception with message and code.
     *
     * @param message   message
     * @param errorCode errorCode
     */
    public CustomException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
