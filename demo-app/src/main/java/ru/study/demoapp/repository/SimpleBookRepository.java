package ru.study.demoapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.study.demoapp.model.Book;
import ru.study.demoapp.service.SlowService;

@Component
public class SimpleBookRepository implements BookRepository {

    private final SlowService slowService;

    @Autowired
    public SimpleBookRepository(SlowService slowService) {
        this.slowService = slowService;
    }

    @Cacheable("books")
    @Override
    public Book getByIsbn(String isbn) {
        slowService.simulateSlowService();
        return new Book(isbn, "Some book");
    }


}
