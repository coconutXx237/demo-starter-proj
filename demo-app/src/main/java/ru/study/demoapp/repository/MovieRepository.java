package ru.study.demoapp.repository;

import ru.study.demoapp.model.Movie;

public interface MovieRepository {
    Movie getByTitle(String title);
}
