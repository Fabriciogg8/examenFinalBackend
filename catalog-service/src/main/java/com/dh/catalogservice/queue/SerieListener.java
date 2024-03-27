package com.dh.catalogservice.queue;



import com.dh.catalogservice.Service.impl.CatalogServiceImpl;
import com.dh.catalogservice.model.Serie;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class SerieListener {
    // Spring no permite la definición de dos beans con el mismo nombre en el contexto de la aplicación
    // Por lo tanto el converter lo coloque solo en Movie
    private final CatalogServiceImpl service;
    public SerieListener(CatalogServiceImpl service) {
        this.service = service;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie){
        service.create(serie);
    }
}