package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="serie-service")
public interface SerieFeignRepository {

    @GetMapping("/api/v1/series")
    List<Serie> getAllSeries();

    @GetMapping("/api/v1/series/{genre}")
    List<Serie> getSeriesByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series")
    @ResponseStatus(HttpStatus.CREATED)
    String createSerie(@RequestBody Serie serie);
}