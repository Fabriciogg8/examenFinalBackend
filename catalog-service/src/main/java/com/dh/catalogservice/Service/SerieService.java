package com.dh.catalogservice.Service;

import com.dh.catalogservice.model.Serie;

import java.util.List;

public interface SerieService {
    List<Serie> getAllSeries();

    List<Serie> getSeriesByGenre(String genre);

    String createSerie(Serie serie);


    void save(Serie serie);
}
