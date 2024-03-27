package com.dh.catalogservice.queue;

import com.dh.catalogservice.Service.SerieService;
import com.dh.catalogservice.model.Serie;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SerieListener {

    private final SerieService serieService;

    public SerieListener(SerieService serieService){
        this.serieService = serieService;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie) {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        serieService.save(serie);
    }

}
