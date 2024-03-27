package com.dh.catalogservice.Service.impl;
import com.dh.catalogservice.Service.SerieService;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.SerieFeignRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SerieServiceImpl implements SerieService {
    private final SerieFeignRepository serieFeignRepository;

    @Override
    public List<Serie> getAllSeries() {
        return serieFeignRepository.getAllSeries();
    }

    @Override
    public List<Serie> getSeriesByGenre(String genre) {
        return serieFeignRepository.getSeriesByGenre(genre);
    }

    @Override
    public String createSerie(Serie serie) {
        return serieFeignRepository.createSerie(serie);
    }

    @Override
    public void save(Serie serie) {

    }


}