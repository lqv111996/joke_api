package com.joke;

import com.joke.controller.JokeController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class JokeApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new JokeApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        final JokeController jokeController = new JokeController();
        environment.jersey().register(jokeController);
    }
}