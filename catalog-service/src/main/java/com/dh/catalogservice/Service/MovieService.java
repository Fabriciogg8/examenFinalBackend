package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.MovieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieService {

    @Autowired
    private MovieFeignRepository movieFeignRepository;

    @CircuitBreaker(name = "movie-service", fallbackMethod = "getMoviesFallbackMethod")
    public ResponseEntity<List<Movie>> findAllMovies() {
        log.info("Calling movie service ...");
        return movieFeignRepository.findAllMovies(true);
    }

//    private List<Movie> getMoviesFallbackMethod(CallNotPermittedException e) {
//        return new ArrayList<>();
//    }

    private ResponseEntity<List<Movie>> getMoviesFallbackMethod(Throwable t) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
    }
}
