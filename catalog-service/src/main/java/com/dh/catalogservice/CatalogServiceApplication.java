package com.dh.catalogservice;

import com.dh.catalogservice.configuration.CustomLoadBalancerForMovie;
import com.dh.catalogservice.configuration.CustomLoadBalancerForSerie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@LoadBalancerClients({
		@LoadBalancerClient(name = "movie-service", configuration = CustomLoadBalancerForMovie.class),
		@LoadBalancerClient(name = "serie-service", configuration = CustomLoadBalancerForSerie.class)
})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

}
