package com.dh.catalogservice.feignClient;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="serie-service")
public interface SerieFeignRepository {

    @GetMapping("/api/v1/series")
    List<Serie> getAllSeries();

    @GetMapping("/api/v1/series/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series/save")
    ResponseEntity<String> createSerie(@RequestBody Serie serie); // Devuelve un ResponseEntity<String>
}