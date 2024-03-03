package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{

    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }
}
