package ru.study.demoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.study.demoapp.repository.MovieRepository;

@Component
public class AppRunnerMovies implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunnerMovies.class);

    private final MovieRepository movieRepository;

    public AppRunnerMovies(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... Fetching movies");
        logger.info("Movie 1 -->" + movieRepository.getByTitle("Movie 1"));
        logger.info("Movie 2 -->" + movieRepository.getByTitle("Movie 2"));
        logger.info("Movie 1 -->" + movieRepository.getByTitle("Movie 1"));
        logger.info("Movie 2 -->" + movieRepository.getByTitle("Movie 2"));
        logger.info("Movie 2 -->" + movieRepository.getByTitle("Movie 2"));
        logger.info("Movie 1 -->" + movieRepository.getByTitle("Movie 1"));
    }
}
