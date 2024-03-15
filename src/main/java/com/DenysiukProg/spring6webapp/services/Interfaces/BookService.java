package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Iterable<Book> findAll();
    List<String> findAllGenre();
    List<Book> findByGenre(String genre);

    List<Book> getBooksByGenreAndAgeGroupIgnoringId(String genre, String ageGroup, Long idToIgnore);

    BookDto findByID(Long id);

    Book saveBook(BookDto bookDto);

    void updateBook(BookDto bookDto);

    void delete(Long id);
}
