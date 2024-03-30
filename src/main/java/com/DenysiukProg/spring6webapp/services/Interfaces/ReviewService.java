package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.entity.Review;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Iterable<Review> findAll();

    Iterable<Review> findAllForBook(BookDto bookDto);

    Iterable<Review> findAllForUser(UserEntity userEntity);
}
