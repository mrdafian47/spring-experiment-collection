package com.github.example.app.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Singleton {

    private static Singleton instance;

    public String value;

    private Singleton(String value) {
        // emulate slow initialization
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Error here: {}", e.getMessage(), e);
        }

        this.value = value;
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }

        return instance;
    }
}
