package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    private final MovieSender movieSender;

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        movieSender.send(movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }

    @GetMapping("/findAll")
    public List<Movie> findAll(@RequestParam(defaultValue = "false") Boolean throwError) {
        return movieService.findAllMovies(throwError);
    }
}
