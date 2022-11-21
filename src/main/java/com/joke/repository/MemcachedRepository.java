package com.joke.repository;

import com.joke.enumuration.ResponseCode;
import com.joke.exception.JokeException;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;

public class MemcachedRepository {
    public static MemcachedRepository INSTANCE = new MemcachedRepository();
    private final String host = "localhost";
    private final int port = 11211;
    private MemcachedClient client;
    public MemcachedRepository() {
        try {
            client = new MemcachedClient(new InetSocketAddress(host, port));
        } catch (Exception e) {
            throw new JokeException(ResponseCode.INTERNAL_ERROR);
        }
    }

    public void set(String key, int expire, String value) {
        client.set(key, expire, value);
    }

    public void incr(String key, Integer value) {
        client.incr(key, value);
    }

    public String get(String key) {
        Object value = client.get(key);
        return value == null ? null : value.toString();
    }

}
