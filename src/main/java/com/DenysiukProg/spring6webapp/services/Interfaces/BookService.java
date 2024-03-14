package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Iterable<Book> findAll();
    List<String> findAllGenre();


    List<Book> getBooksByGenreAndAgeGroupIgnoringId(String genre, String ageGroup, Long idToIgnore);

    BookDto findByID(Long id);

    void saveBook(Book bookDto);

    void updateBook(BookDto bookDto);
}
