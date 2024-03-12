package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Iterable<Book> findAll();
    List<String> findAllGenre();

    List<Book> findBooksByGenreAndAgeGroup(String genre, String ageGroup);

    BookDto finByID(Long id);

    void saveBook(Book bookDto);
}
