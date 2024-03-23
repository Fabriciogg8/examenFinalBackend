package com.dh.catalogservice.Service.impl;

import com.dh.catalogservice.Service.MovieService;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.MovieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    private MovieFeignRepository movieFeignRepository;

    @Autowired
    public MovieServiceImpl (MovieFeignRepository movieFeignRepository){
        this.movieFeignRepository = movieFeignRepository;
    }

    @CircuitBreaker(name = "movie-service", fallbackMethod = "getMoviesFallbackMethod")
    @Override
    public List<Movie> findAllMovies() {
        log.info("Calling movie service ...");
        return movieFeignRepository.findAllMovies(true);
    }

    private List<Movie> getMoviesFallbackMethod(CallNotPermittedException e) {
        return new ArrayList<>();
    }


}

