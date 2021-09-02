package com.example.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class CustomController {

    private AtomicInteger x = new AtomicInteger(0);

    @GetMapping("")
    public String get() {
        if (x.incrementAndGet() % 2 == 0) {
            throw new RuntimeException("Testing sentry, x=" + x.get());
        }
        return "Hello";
    }

}
