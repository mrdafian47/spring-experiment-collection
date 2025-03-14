package com.github.example.app.singleton;

public final class LazyLoadSingleton {

    private static volatile LazyLoadSingleton instance;

    public String value;

    private LazyLoadSingleton(String value) {
        this.value = value;
    }

    public static LazyLoadSingleton getInstance(String value) {
        LazyLoadSingleton result = instance;
        if (result != null) {
            return result;
        }

        synchronized (LazyLoadSingleton.class) {
            if (instance == null) {
                instance = new LazyLoadSingleton(value);
            }

            return instance;
        }
    }
}
