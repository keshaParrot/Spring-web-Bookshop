package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    Iterable<Book> findAll();
    List<String> findAllGenre();

    Optional<Book> finByID(Long id);

    void saveBook(Book bookDto);
}
