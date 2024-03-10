package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.dto.BookDto;
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
    public Optional<Book> finByID(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void saveBook(Book book){
        bookRepository.save(book);
        System.out.println("\nbook saved\n");
    }
    private BookDto mapToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setIsbn(book.getIsbn());
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
}
