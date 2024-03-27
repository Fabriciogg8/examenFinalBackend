package com.dh.catalogservice.Service.impl;

import com.dh.catalogservice.Service.CatalogService;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    // Listas vacías para usar como respuesta de fallback en caso de error
    ArrayList<Movie> moviesEmpty = new ArrayList<>();
    ArrayList<Serie> seriesEmpty = new ArrayList<>();

    // Repositorios para acceder a la base de datos MongoDB
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;

    // Constructor que recibe los repositorios como parámetros de inyección de dependencias
    public CatalogServiceImpl(MovieRepository movieRepository, SerieRepository serieRepository) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

    // Método para obtener el catálogo por género
    @Override
    public Genre getCatalogByGenre(String genre) {
        // Buscar películas y series por género en la base de datos
        List<Movie> movies = movieRepository.findByGenre(genre);
        List<Serie> series = serieRepository.findByGenre(genre);
        // Devolver un objeto Genre que contiene las películas y series encontradas
        return new Genre(movies, series);
    }

    // Método de fallback para la obtención del catálogo por género
    public Genre getCatalogFallbackMethod(String genre, Throwable throwable) {
        // Registro de información sobre el género para el cual se produjo un error
        log.info(genre);
        // Devolver un objeto Genre vacío en caso de error
        return new Genre(moviesEmpty, seriesEmpty);
    }

    // Método para guardar una película en la base de datos
    @Override
    public Movie saveMovie(Movie movie) {
        // Guardar la película directamente en la base de datos MongoDB
        return movieRepository.save(movie);
    }

    // Método para crear una serie en la base de datos
    @Override
    public Serie create(Serie serie) {
        // Crear la serie directamente en la base de datos MongoDB
        return serieRepository.save(serie);
    }
}
