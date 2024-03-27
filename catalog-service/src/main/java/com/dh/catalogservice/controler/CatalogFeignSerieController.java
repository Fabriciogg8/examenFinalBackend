package com.dh.catalogservice.controler;

import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.feignClient.SerieFeignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/catalog")
public class CatalogFeignSerieController {

    @Autowired
    private SerieFeignRepository serieFeignRepository;

    @GetMapping("/series")
    public List<Serie> getAllSeries() {
        return serieFeignRepository.getAllSeries();
    }

    @GetMapping("/series/{genre}")
    public ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre) {
        return serieFeignRepository.getSeriesByGenre(genre);
    }

    @PostMapping("/series")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createSerie(@RequestBody Serie serie) {
        return serieFeignRepository.createSerie(serie);
    }

}
