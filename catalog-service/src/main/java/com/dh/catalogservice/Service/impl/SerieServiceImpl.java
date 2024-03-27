package com.dh.catalogservice.Service.impl;
import com.dh.catalogservice.Service.SerieService;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.feignClient.SerieFeignRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor // Anotación que genera un constructor con argumentos para los campos marcados como final
@Slf4j // Anotación para agregar un logger a la clase
@Service
public class SerieServiceImpl implements SerieService { // Implementa la interfaz SerieService

    // Repositorio Feign para las llamadas al servicio de series
    private SerieFeignRepository serieFeignRepository;

    @Autowired // Inyección de dependencia del repositorio SerieFeignRepository en el constructor
    public SerieServiceImpl(SerieFeignRepository serieFeignRepository) {
        this.serieFeignRepository = serieFeignRepository; // Asignación del repositorio SerieFeignRepository
    }

    // Método para obtener todas las series, protegido por un circuit breaker
    @CircuitBreaker(name = "serie-service", fallbackMethod = "fallbackForSeries")
    @Override
    public List<Serie> getAllSeries() {
        log.info("Llamando al servicio de series para obtener todas las series..."); // Registro de información
        return serieFeignRepository.getAllSeries(); // Llamada al Feign client para obtener todas las series
    }

    // Método para obtener series por género, protegido por un circuit breaker
    @CircuitBreaker(name = "serie-service", fallbackMethod = "fallbackForSeries")
    @Override
    public List<Serie> getSeriesByGenre(String genre) {
        log.info("Llamando al servicio de series para obtener series por género: {}", genre); // Registro de información
        return serieFeignRepository.getSeriesByGenre(genre); // Llamada al Feign client para obtener series por género
    }

    // Método para crear una nueva serie, protegido por un circuit breaker
    @CircuitBreaker(name = "serie-service", fallbackMethod = "fallbackForCreateSerie")
    @Override
    public String createSerie(Serie serie) {
        log.info("Creando nueva serie: {}", serie); // Registro de información
        return serieFeignRepository.createSerie(serie); // Llamada al Feign client para crear una nueva serie
    }

    // Método de fallback para getAllSeries y getSeriesByGenre
    private List<Serie> fallbackForSeries(Exception e) {
        log.error("Método de fallback llamado para el servicio de series debido a {}", e.toString()); // Registro de error
        return Collections.emptyList(); // Devuelve una lista vacía como fallback
    }

    // Método de fallback para createSerie
    private String fallbackForCreateSerie(Exception e) {
        log.error("Método de fallback llamado para el servicio de creación de serie debido a {}", e.toString()); // Registro de error
        return "ID de fallback"; // Devuelve un ID de fallback como fallback
    }
}