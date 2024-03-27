package com.dh.catalogservice.Service.impl;


import com.dh.catalogservice.Service.CatalogService;
import com.dh.catalogservice.feignClient.MovieFeignRepository;
import com.dh.catalogservice.feignClient.SerieFeignRepository;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.Service.MovieService;
import com.dh.catalogservice.Service.SerieService;
import com.dh.catalogservice.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    ArrayList<Movie> moviesEmpty = new ArrayList<>();
    ArrayList<Serie> seriesEmpty = new ArrayList<>();
    private MovieFeignRepository iMovieClient;
    private SerieFeignRepository iSerieClient;
    private MovieService movieService;
    private SerieRepository serieRepository;

    public CatalogServiceImpl(MovieFeignRepository iMovieClient, SerieFeignRepository iSerieClient, MovieService movieService, SerieRepository serieRepository) {
        this.iMovieClient = iMovieClient;
        this.iSerieClient = iSerieClient;
        this.movieService = movieService;
        this.serieRepository = serieRepository;
    }

    @CircuitBreaker(name="catalog", fallbackMethod = "getCatalogFallbackMethod")
    @Retry(name="catalog")
    @Override
    public Genre getCatalogByGenre(String genre){

        ResponseEntity<List<Movie>> movieList = iMovieClient.getMovieByGenre(genre);
        ResponseEntity<List<Serie>> serieList = iSerieClient.getSeriesByGenre(genre);
        if (movieList.getStatusCode().is2xxSuccessful()) {
            return new Genre(movieList.getBody(), serieList.getBody());
        } else {
            return new Genre(movieService.getMoviesByGenre(genre, true), serieRepository.findByGenre(genre));
        }
    }


    public Genre getCatalogFallbackMethod(String genre, Throwable throwable) {
        log.info(genre);
        Genre resultsByGenre = new Genre(moviesEmpty, seriesEmpty);
        return resultsByGenre;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        Movie savedMovie;
        ResponseEntity<Movie> movieResponseEntity = iMovieClient.saveMovie(movie);
        savedMovie = movieResponseEntity.getBody();
        return savedMovie;
    }

    // saveSerie
    @Override
    public Serie create(Serie serie) {
        ResponseEntity<String> serieSaved = iSerieClient.createSerie(serie);
        if (serieSaved.getStatusCode().is2xxSuccessful()) {
            return serie; // Devuelve la serie si la creación fue exitosa
        } else {
            return null; // O maneja el caso de error de alguna otra manera
        }
    }

}
