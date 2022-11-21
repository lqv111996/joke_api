package com.joke.controller;

import com.codahale.metrics.annotation.Timed;
import com.joke.dto.common.ResponseDto;
import com.joke.enumuration.ResponseCode;
import com.joke.exception.JokeException;
import com.joke.service.JokeRateLimitService;
import com.joke.service.JokeService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/jokes")
@Produces(MediaType.APPLICATION_JSON)
public class JokeController {
    private JokeService jokeService = JokeService.INSTANCE;
    private JokeRateLimitService rateLimitService = JokeRateLimitService.INSTANCE;

    @GET
    @Timed
    public ResponseDto<List<String>> search(@QueryParam("keyword") @NotEmpty @NotNull String keyword) {
        if (keyword.length() < 3) {
            return ResponseDto.of(ResponseCode.INVALID_KEYWORD_LENGTH, null);
        }

        try {
            rateLimitService.rateLimit(keyword.trim());
            return ResponseDto.ok(jokeService.getJokes(keyword.trim()));
        } catch (Exception e) {
            if (e instanceof JokeException) {
                return ResponseDto.of(((JokeException) e).getCode(), null);
            } else {
                return ResponseDto.of(ResponseCode.INTERNAL_ERROR, null);
            }
        }
    }
}
