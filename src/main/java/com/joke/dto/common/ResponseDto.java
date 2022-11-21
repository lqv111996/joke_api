package com.joke.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joke.enumuration.ResponseCode;

public class ResponseDto<T> {
    private int code;
    private T body;

    private String message;

    public ResponseDto(int code, T body, String message) {
        this.code = code;
        this.body = body;
        this.message = message;
    }

    public static<R> ResponseDto<R> of(ResponseCode responseCode, R body) {
        return new ResponseDto<>(responseCode.getCode(), body, responseCode.getMessage());
    }

    public static ResponseDto<String> of(ResponseCode responseCode) {
        return new ResponseDto<>(responseCode.getCode(),null, responseCode.getMessage());
    }

    public static<R> ResponseDto<R> ok(R body) {
        return of(ResponseCode.OK, body);
    }

    public static ResponseDto<String> ok() {
        return of(ResponseCode.OK);
    }

    @JsonProperty
    public int getCode() {
        return code;
    }

    @JsonProperty
    public T getBody() {
        return body;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }
}