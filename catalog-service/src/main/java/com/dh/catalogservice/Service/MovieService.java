package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAllMovies();

    List<Movie> getMoviesByGenre(String genre, Boolean throwError);
}
