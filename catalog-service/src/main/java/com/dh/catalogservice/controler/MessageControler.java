package com.dh.catalogservice.controler;

import com.dh.catalogservice.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class MessageControler {

//    @Value("${message}")
    private String message;
    @Autowired
    private MovieService movieService;
    @GetMapping("/message")
    public String getMessage() {

        return message = "Hola"+movieService.findAllMovies();

    }
}
