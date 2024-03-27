package com.dh.catalogservice.controler;

import com.dh.catalogservice.Service.CatalogService;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/movies/{genre}")
    public Genre getMoviesByGenre(@PathVariable String genre) {
        return catalogService.getCatalogByGenre(genre);
    }

    @PostMapping("/movies")
    public Movie saveMovie(@RequestBody Movie movie) {
        return catalogService.saveMovie(movie);
    }

    @PostMapping("/series")
    public Serie createSerie(@RequestBody Serie serie) {
        return catalogService.create(serie);
    }
}