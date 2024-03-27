package com.dh.catalogservice.configuration;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomLoadBalancerForMovie {
    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancerForMovie(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory){
        String name = "movie-service"; // Nombre del servicio de películas
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}