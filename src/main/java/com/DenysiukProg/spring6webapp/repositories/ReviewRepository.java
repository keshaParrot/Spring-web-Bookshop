package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import com.DenysiukProg.spring6webapp.domain.entity.Review;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for working with Review table on database.
 */
public interface ReviewRepository extends CrudRepository<Review,Long> {

    /**
     * Finds reviews by book.
     *
     * @param book The book to search for reviews.
     * @return Iterable of reviews related to the specified book.
     */
    Iterable<Review> findByBook(Book book);

    /**
     * Finds reviews by user.
     *
     * @param userEntity The user whose reviews to retrieve.
     * @return Iterable of reviews written by the specified user.
     */
    Iterable<Review> findByUser(UserEntity userEntity);
}
