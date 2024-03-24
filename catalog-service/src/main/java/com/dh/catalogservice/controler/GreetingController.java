package com.dh.catalogservice.controler;

import com.dh.catalogservice.repository.MovieFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class GreetingController {

    @Autowired
    private MovieFeignRepository movieFeignRepository;

    @GetMapping("/greeting")
    public String getGreeting() {
        return movieFeignRepository.getGreeting();
    }
}
