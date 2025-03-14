package com.github.example.app.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LazyLoadSingletonTest {

    @Test
    void example_multi_threaded_singleton() {

        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());

        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            LazyLoadSingleton singleton = LazyLoadSingleton.getInstance("FOO");

            assertEquals("FOO", singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            LazyLoadSingleton singleton = LazyLoadSingleton.getInstance("BAR");

            assertEquals("FOO", singleton.value);
        }
    }
}