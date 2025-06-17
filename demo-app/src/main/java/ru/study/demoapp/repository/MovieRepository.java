package ru.study.demoapp.repository;

import ru.study.demoapp.model.Movie;

public interface MovieRepository {
    public Movie getByTitle(String title);
}
