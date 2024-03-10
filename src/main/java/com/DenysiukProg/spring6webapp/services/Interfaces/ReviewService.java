package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Review;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Iterable<Review> findAll();
}
