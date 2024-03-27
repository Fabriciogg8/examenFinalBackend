package com.dh.catalogservice.feignClient;

import com.dh.catalogservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="movie-service")
public interface MovieFeignRepository {

    @GetMapping("/api/v1/movies/{genre}") // Ruta para consultar las peliculas por genero
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre); // Método para devolver las peliculas por genero

    @PostMapping("/api/v1/movies/save") // Ruta para guardar una película
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie); // Método para guardar una película

    @GetMapping("/api/v1/movies/findAll") // Ruta para consultar todas las peliculas
    List<Movie> findAllMovies(@RequestParam Boolean throwError); // Método para devolver todas las peliculas

}