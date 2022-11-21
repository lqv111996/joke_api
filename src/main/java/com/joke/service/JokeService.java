package com.joke.service;

import com.joke.http.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JokeService {
    private final HttpUtil http = HttpUtil.INSTANCE;
    private final String url = "https://api.chucknorris.io/jokes/search?query=";

    public static JokeService INSTANCE = new JokeService();

    public List<String> getJokes(String keyword) {
        String response = http.get(url + keyword);

        List<String> result = getJokeValues(response);
        result = result.stream().filter(value -> {
            value = value.trim();
            value = " " + value + " ";
            return value.matches("(.*) " + keyword +  " (.*)");
        }).collect(Collectors.toList());

        return result;
    }

    private List<String> getJokeValues(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<String> result = new ArrayList<>();
        for (int i = 0 ; i < jsonArray.length(); i++) {
            JSONObject tmp = jsonArray.getJSONObject(i);
            Object value = tmp.get("value");
            result.add(value.toString());
        }

        return result;
    }
}
