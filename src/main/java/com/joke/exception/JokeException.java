package com.joke.exception;

import com.joke.enumuration.ResponseCode;

public class JokeException extends RuntimeException {

    private final ResponseCode code;
    public JokeException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode;
    }

    public JokeException(ResponseCode responseCode, String message) {
        super(message);
        this.code = responseCode;
    }

    public ResponseCode getCode() {
        return code;
    }
}