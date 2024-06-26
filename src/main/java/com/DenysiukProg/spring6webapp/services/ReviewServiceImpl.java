package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Mappers.BookMapper;
import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Review;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.repositories.BookRepository;
import com.DenysiukProg.spring6webapp.repositories.ReviewRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }
    @Override
    public Iterable<Review> findAllForBook(BookDto bookDto){
        Book book = BookMapper.DtoToEntity(bookDto);
        Book persistedBook = bookRepository.findById(book.getId()).orElse(null);

        if (persistedBook != null) {
            return reviewRepository.findByBook(persistedBook);
        } else {
            return null;
        }
    }
    @Override
    public Iterable<Review> findAllForUser(UserEntity userEntity){
        return reviewRepository.findByUser(userEntity);
    }

}
