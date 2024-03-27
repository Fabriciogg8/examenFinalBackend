package com.dh.movieservice.queue;

import com.dh.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableRabbit
public class MovieSender {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;
    public void send(Movie movie){
        this.rabbitTemplate.convertAndSend(this.queue.getName(), movie);
    }
}
