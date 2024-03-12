package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.repositories.ReviewRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }
    @Override
    public Iterable<Review> findAllForBook(BookDto book){
        return reviewRepository.findByBook(book);
    }
    @Override
    public Iterable<Review> findAllForUser(UserEntity userEntity){
        return reviewRepository.findByUser(userEntity);
    }
}
