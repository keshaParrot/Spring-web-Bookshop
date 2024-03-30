package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    Optional<Integer> findMinPrice();

    Optional<Integer> findMaxPrice();

    String count();

    List<Book> findFilteredBooks(String searchTerm, Double minPrice, Double maxPrice, List<String> genres);
}
