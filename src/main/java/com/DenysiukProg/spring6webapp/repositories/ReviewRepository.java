package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Review;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    Iterable<Review> findByBook(Book book);
    Iterable<Review> findByUser(UserEntity userEntity);
}
