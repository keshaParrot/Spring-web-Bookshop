package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Iterable<Review> findAll();

    Iterable<Review> findAllForBook(BookDto book);

    Iterable<Review> findAllForUser(UserEntity userEntity);
}
