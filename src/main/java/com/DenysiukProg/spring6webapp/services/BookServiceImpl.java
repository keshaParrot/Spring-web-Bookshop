package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.DenysiukProg.spring6webapp.domain.Mappers.BookMapper.DtoToEntity;
import static com.DenysiukProg.spring6webapp.domain.Mappers.BookMapper.entityToDto;

/**
 * Service class for managing books in the repository.
 * Implements business logic associated with book data.
 */
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public List<String> findAllGenre() {
        return bookRepository.findAllGenres();
    }
    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    @Override
    public List<Book> getBooksByGenreAndAgeGroupIgnoringId(String genre, String ageGroup, Long idToIgnore) {
        return bookRepository.findByGenreAndAgeGroupAndIdNot(genre, ageGroup, idToIgnore);
    }
    @Override
    public BookDto findByID(Long id) {
        Book book = bookRepository.findById(id).get();
        return entityToDto(book);
    }

    @Override
    public Book saveBook(BookDto bookDto){
        Book book = DtoToEntity(bookDto);
        System.out.println("\nbook saved\n");
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookRepository.save(DtoToEntity(bookDto));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Integer> findMinPrice() {
        return bookRepository.findMinPrice();
    }
    @Override
    public Optional<Integer> findMaxPrice() {
        return bookRepository.findMaxPrice();
    }
    @Override
    public String count(){
        return String.valueOf(bookRepository.count());
    }
    @Override
    public List<Book> findFilteredBooks(String searchTerm, Double minPrice, Double maxPrice, List<String> genres) {
        return bookRepository.findWithFilters(searchTerm, minPrice, maxPrice, genres);
    }


}
