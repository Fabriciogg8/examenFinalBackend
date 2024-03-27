package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Movie;

import java.util.List;

public interface MovieService {

    // Método para encontrar todas las películas
    List<Movie> findAllMovies();

    // Método para obtener películas por género
    List<Movie> getMoviesByGenre(String genre, Boolean throwError);
}
