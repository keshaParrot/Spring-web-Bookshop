package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.repositories.AuthorRepository;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
    public List<Book> getBooksByGenreAndAgeGroupIgnoringId(String genre, String ageGroup, Long idToIgnore) {
        return bookRepository.findByGenreAndAgeGroupAndIdNot(genre, ageGroup, idToIgnore);
    }
    @Override
    public BookDto findByID(Long id) {
        Book book = bookRepository.findById(id).get();
        return entityToDto(book);
    }

    @Override
    public void saveBook(Book book){
        bookRepository.save(book);
        System.out.println("\nbook saved\n");
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookRepository.save(DtoToEntity(bookDto));
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
        bookDto.setDescription(book.getDescription());
        bookDto.setPrice(book.getPrice());
        bookDto.setGenre(book.getGenre());

        bookDto.setAuthorIds(book.getAuthors());
        bookDto.setPublisherId(book.getPublisher());

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
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setGenre(bookDto.getGenre());

        book.setAuthors(bookDto.getAuthorIds());
        book.setPublisher(bookDto.getPublisherId());
        return book;
    }
}
