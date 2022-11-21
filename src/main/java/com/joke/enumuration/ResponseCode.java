package com.joke.enumuration;

public enum ResponseCode {
    OK(200, "Ok"),
    BAD_REQUEST(400, "Bad request"),
    NOT_FOUND(404, "Not found"),
    UNAUTHORIZED(401, "Unauthorized"),
    RATE_LIMITED(402, "RateLimited"),
    INVALID_KEYWORD_LENGTH(403, "Length of keyword greater than or equals three"),
    INTERNAL_ERROR(500, "Internal error");

    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}