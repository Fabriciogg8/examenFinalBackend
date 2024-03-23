package com.dh.catalogservice.controler;

import com.dh.catalogservice.Service.MovieService;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.MovieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RefreshScope
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private MovieFeignRepository movieFeignRepository;

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        ResponseEntity<List<Movie>> response = movieFeignRepository.getMovieByGenre(genre);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieService.findAllMovies();
    }
}
