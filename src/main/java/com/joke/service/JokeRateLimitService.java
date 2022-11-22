package com.joke.service;

import com.joke.enumuration.ResponseCode;
import com.joke.exception.JokeException;
import com.joke.repository.MemcachedRepository;

public class JokeRateLimitService {
    private final String keyFormat = "joke_keyword_%s";
    private final int SIXTY_SECONDS = 60;
    private final int MAX_TIMES_PER_MINUTE = 5;

    private JokeRateLimitService() {}
    private final MemcachedRepository memcachedRepository = MemcachedRepository.INSTANCE;

    public static JokeRateLimitService INSTANCE = new JokeRateLimitService();

    public synchronized void rateLimit(String keyword) {
        String key = String.format(keyFormat, keyword);

        String value = memcachedRepository.get(key);
        if (value == null) {
            memcachedRepository.set(key, SIXTY_SECONDS, String.valueOf(1));
        } else {
            int times = Integer.valueOf(value);
            if (times < MAX_TIMES_PER_MINUTE) {
                memcachedRepository.incr(key, 1);
            } else {
                throw new JokeException(ResponseCode.RATE_LIMITED);
            }
        }
    }
}
