package com.dh.catalogservice.controler;

import com.dh.catalogservice.repository.MovieFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class GreetingController {

    @Autowired
    private MovieFeignRepository movieFeignRepository;
    @Autowired
    private DiscoveryClient discoveryClient;
//    @GetMapping("/greeting")
//    public String greet() {
//
//        return movieFeignRepository.getGreeting();
//    }
    @GetMapping("/greeting")
    public String greet() {
        // Llama al servicio de saludo usando Feign
        String greeting = movieFeignRepository.getGreeting();

        // Obtiene información sobre la instancia del servicio movie-service
        ServiceInstance instance = discoveryClient.getInstances("movie-service").get(0);
        String instanceInfo = "Instance: " + instance.getHost() + ":" + instance.getPort();

        return greeting + " (" + instanceInfo + ")";
    }
}
