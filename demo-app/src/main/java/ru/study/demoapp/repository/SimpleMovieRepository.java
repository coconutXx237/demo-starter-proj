package ru.study.demoapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.study.demoapp.model.Movie;
import ru.study.demoapp.service.SlowService;

@Component
public class SimpleMovieRepository implements MovieRepository{

    private final SlowService slowService;

    @Autowired
    public SimpleMovieRepository(SlowService slowService) {
        this.slowService = slowService;
    }

//    @Cacheable(value = "moviesCache", cacheManager = "redisCacheManager")
    @Override
    public Movie getByTitle(String title) {
        slowService.simulateSlowService();
        return new Movie(title);
    }
}
