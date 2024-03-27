package com.dh.catalogservice.Service.impl;
import com.dh.catalogservice.Service.SerieService;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.feignClient.SerieFeignRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Slf4j // Anotación para agregar un logger a la clase
@Service
public class SerieServiceImpl implements SerieService {

    private final SerieFeignRepository serieFeignRepository;

    // Constructor que inyecta el SerieFeignRepository
    public SerieServiceImpl(SerieFeignRepository serieFeignRepository) {
        this.serieFeignRepository = serieFeignRepository;
    }

    // Método para obtener todas las series
    @Override
    public List<Serie> getAllSeries() {
        return null; // En este método podrías implementar la lógica para obtener todas las series
    }

    // Método para obtener series por género, protegido por un circuit breaker
    @CircuitBreaker(name = "serie-service", fallbackMethod = "fallbackForSeries")
    @Override
    public List<Serie> getSeriesByGenre(String genre) {
        log.info("Llamando al servicio de series para obtener series por género: {}", genre); // Registro de información
        ResponseEntity<List<Serie>> response = serieFeignRepository.getSeriesByGenre(genre); // Llamada al Feign client para obtener series por género
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Devuelve las series obtenidas si la solicitud fue exitosa
        } else {
            throw new RuntimeException("Error al obtener series por género: " + response.getStatusCode()); // Lanza una excepción si la solicitud falla
        }
    }

    // Método para crear una nueva serie, protegido por un circuit breaker
    @CircuitBreaker(name = "serie-service", fallbackMethod = "fallbackForCreateSerie")
    @Override
    public String createSerie(Serie serie) {
        log.info("Creando nueva serie: {}", serie); // Registro de información
        ResponseEntity<String> response = serieFeignRepository.createSerie(serie); // Llamada al Feign client para crear una nueva serie
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Devuelve el ID de la nueva serie si la creación fue exitosa
        } else {
            throw new RuntimeException("Error al crear una nueva serie: " + response.getStatusCode()); // Lanza una excepción si la creación falla
        }
    }

    // Método de fallback para getSeriesByGenre
    private List<Serie> fallbackForSeries(String genre, Exception e) {
        log.error("Método de fallback llamado para obtener series por género debido a {}", e.toString()); // Registro de error
        return Collections.emptyList(); // Devuelve una lista vacía como fallback
    }

    // Método de fallback para createSerie
    private String fallbackForCreateSerie(Serie serie, Exception e) {
        log.error("Método de fallback llamado para crear una nueva serie debido a {}", e.toString()); // Registro de error
        return "ID de fallback"; // Devuelve un ID de fallback como fallback
    }
}