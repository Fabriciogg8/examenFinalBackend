package com.dh.catalogservice.queue;

import com.dh.catalogservice.Service.impl.CatalogServiceImpl;
import com.dh.catalogservice.model.Movie;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MovieListener {
    private final CatalogServiceImpl service;
    public MovieListener(CatalogServiceImpl service) {
        this.service = service;
    }

//    @Bean
//    public Jackson2JsonMessageConverter converter(){
//        return new Jackson2JsonMessageConverter();
//    };
    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie){
        service.saveMovie(movie);
    }
}
