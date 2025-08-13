package com.example.ChatApp.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

public class EnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("DATABASE_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("DATABASE_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
    }
}