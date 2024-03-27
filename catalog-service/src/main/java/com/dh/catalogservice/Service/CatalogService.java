package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;

public interface CatalogService {

    // Método para obtener el catálogo por género
    Genre getCatalogByGenre(String genre);

    // Método para guardar una película
    Movie saveMovie(Movie movie);

    // Método para crear una serie
    Serie create(Serie serie);
}