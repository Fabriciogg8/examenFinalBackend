//package com.dh.catalogservice.repository;
//
//import com.dh.catalogservice.model.Movie;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@FeignClient(name ="serie-service")
//public interface SerieFeignRepository {
//
//
//    @GetMapping("/api/v1/series/{genre}")
//    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre);
//
//}
