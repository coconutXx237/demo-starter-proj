package ru.study.demoapp.repository;

import ru.study.demoapp.model.Book;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
