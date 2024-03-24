package com.dh.movieservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RefreshScope
@RestController
public class GreetingController {

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/greeting")
    public String getGreeting() {
        return "Puerto del servidor: " + serverPort;
    }
}
