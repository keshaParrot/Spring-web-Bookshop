package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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

    public static BookDto entityToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPublicationDate(book.getPublicationDate());

        bookDto.setAgeGroup(book.getAgeGroup());
        bookDto.setNumberOfPages(book.getNumberOfPages());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPhotoURL(book.getPhotoURL());
        bookDto.setDescriptionParagraph1(book.getDescriptionParagraph1());
        bookDto.setDescriptionParagraph2(book.getDescriptionParagraph2());
        bookDto.setDescriptionParagraph3(book.getDescriptionParagraph3());
        bookDto.setPrice(book.getPrice());
        bookDto.setGenre(book.getGenre());

        bookDto.setAuthors(book.getAuthors());
        bookDto.setPublisher(book.getPublisher());

        return bookDto;
    }
    public static Book DtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());

        book.setAgeGroup(bookDto.getAgeGroup());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setLanguage(bookDto.getLanguage());
        book.setPhotoURL(bookDto.getPhotoURL());
        book.setDescriptionParagraph1(bookDto.getDescriptionParagraph1());
        book.setDescriptionParagraph2(bookDto.getDescriptionParagraph2());
        book.setDescriptionParagraph3(bookDto.getDescriptionParagraph3());
        book.setPrice(bookDto.getPrice());
        book.setGenre(bookDto.getGenre());

        book.setAuthors(bookDto.getAuthors());
        book.setPublisher(bookDto.getPublisher());
        return book;
    }
}
