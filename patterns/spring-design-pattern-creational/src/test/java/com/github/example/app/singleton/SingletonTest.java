package com.github.example.app.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SingletonTest {

    @Test
    void example_single_threaded_singleton() {

        Singleton singleton = Singleton.getInstance("FOO");
        Singleton otherSingleton = Singleton.getInstance("BAR");

        assertEquals(singleton.value, otherSingleton.value);
    }

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
            Singleton singleton = Singleton.getInstance("FOO");

            assertEquals("FOO", singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");

            assertEquals("BAR", singleton.value);
        }
    }
}