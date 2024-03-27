package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Serie;

import java.util.List;
public interface SerieService {

    // Método para obtener todas las series
    List<Serie> getAllSeries();

    // Método para obtener series por género
    List<Serie> getSeriesByGenre(String genre);

    // Método para crear una nueva serie
    String createSerie(Serie serie);

}
