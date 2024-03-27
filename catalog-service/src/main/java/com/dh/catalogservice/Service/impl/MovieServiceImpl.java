package com.dh.catalogservice.Service.impl;

import com.dh.catalogservice.Service.MovieService;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.feignClient.MovieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j // Anotación para agregar un logger a la clase
@Service
public class MovieServiceImpl implements MovieService {

    private MovieFeignRepository movieFeignRepository;

    // Constructor que inyecta el MovieFeignRepository
    @Autowired
    public MovieServiceImpl(MovieFeignRepository movieFeignRepository){
        this.movieFeignRepository = movieFeignRepository;
    }

    // Método para obtener todas las películas
    @CircuitBreaker(name = "movie-service", fallbackMethod = "getMoviesFallbackMethod")
    @Override
    public List<Movie> findAllMovies() {
        log.info("Calling movie service ..."); // Registro de información
        return movieFeignRepository.findAllMovies(true); // Llamada al Feign client para obtener todas las películas
    }

    // Método para obtener películas por género
    @Override
    public List<Movie> getMoviesByGenre(String genre, Boolean throwError) {
        return null; // En este método podrías implementar la lógica para obtener películas por género
    }

    // Método de fallback para findAllMovies
    private List<Movie> getMoviesFallbackMethod(CallNotPermittedException e) {
        return new ArrayList<>(); // Devuelve una lista vacía como fallback
    }
}

