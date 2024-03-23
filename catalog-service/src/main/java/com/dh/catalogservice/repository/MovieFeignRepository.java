package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name ="movie-service")
public interface MovieFeignRepository {

    @GetMapping("/api/v1/movies/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre);

    @GetMapping("/greeting")
    String getGreeting();

    @GetMapping("/api/v1/movies/findAll")
    List<Movie> findAllMovies(@RequestParam Boolean throwError);

}