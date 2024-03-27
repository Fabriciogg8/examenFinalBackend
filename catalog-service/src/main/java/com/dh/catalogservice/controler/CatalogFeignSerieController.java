package com.dh.catalogservice.controler;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.queue.SerieListener;
import com.dh.catalogservice.repository.SerieFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class CatalogFeignSerieController {

    @Autowired
    private SerieFeignRepository serieFeignRepository;

    private final SerieListener listener;

    public CatalogFeignSerieController(SerieListener listener) {
        this.listener = listener;
    }

    @GetMapping("/series")
    public List<Serie> getAllSeries() {
        return serieFeignRepository.getAllSeries();
    }

    @GetMapping("/series/{genre}")
    public List<Serie> getSeriesByGenre(@PathVariable String genre) {
        return serieFeignRepository.getSeriesByGenre(genre);
    }

    @PostMapping("/series")
    @ResponseStatus(HttpStatus.CREATED)
    public String createSerie(@RequestBody Serie serie) {
        listener.receive(serie);
        return serieFeignRepository.createSerie(serie);
    }

}
