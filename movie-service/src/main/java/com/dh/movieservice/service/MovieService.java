package com.dh.movieservice.service;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    // Agregada para probar el circuitbraker
    public List<Movie> findAllMovies(Boolean throwError){
        if(throwError)
            throw new RuntimeException();
        return movieRepository.findAll();
        }
}
