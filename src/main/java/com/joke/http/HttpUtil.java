package com.joke.http;

import com.joke.enumuration.ResponseCode;
import com.joke.exception.JokeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.glassfish.jersey.client.JerseyClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class HttpUtil {

    private CloseableHttpClient httpClient;
    public static HttpUtil INSTANCE = new HttpUtil();
    public HttpUtil() {
        httpClient = HttpClients.createDefault();
    }

    public String get(String url) {
        HttpGet request = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code != ResponseCode.OK.getCode()) {
                throw new JokeException(ResponseCode.INTERNAL_ERROR);
            }

            HttpEntity entity = response.getEntity();
            if (entity == null || entity.toString().isEmpty()) {
                throw new JokeException(ResponseCode.INTERNAL_ERROR);
            }

            return EntityUtils.toString(entity);
        } catch (Exception e) {
            throw new JokeException(ResponseCode.INTERNAL_ERROR);
        }
    }
}
