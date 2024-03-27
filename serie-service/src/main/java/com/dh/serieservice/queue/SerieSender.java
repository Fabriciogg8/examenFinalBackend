package com.dh.serieservice.queue;

import com.dh.serieservice.model.Serie;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableRabbit
public class SerieSender {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;
    public void send(Serie serie){
        this.rabbitTemplate.convertAndSend(this.queue.getName(), serie);
    }
}
