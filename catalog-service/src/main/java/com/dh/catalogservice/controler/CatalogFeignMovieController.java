package com.dh.catalogservice.controler;

import com.dh.catalogservice.Service.MovieService;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.feignClient.MovieFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class CatalogFeignMovieController {

    @Autowired
    private MovieFeignRepository movieFeignRepository;

    private static java.util.logging.Logger log = Logger.getLogger(CatalogFeignMovieController.class.getName());

    // Método para obtener películas por género
    @GetMapping("/movie/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        log.info("Se visualiza correctamente la película");
        ResponseEntity<List<Movie>> response = movieFeignRepository.getMovieByGenre(genre);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // Método para obtener todas las películas
    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam(required = false) Boolean throwError) {
        log.info("Se visualiza correctamente la película");
        return movieFeignRepository.findAllMovies(throwError);
    }

    // Método para guardar una película
    @PostMapping("/movie") // Nuevo endpoint para guardar una película
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        ResponseEntity<Movie> response = movieFeignRepository.saveMovie(movie);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}