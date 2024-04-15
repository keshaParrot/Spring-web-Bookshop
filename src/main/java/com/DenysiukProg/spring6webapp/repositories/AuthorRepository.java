package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for working with authors table on database.
 */
public interface AuthorRepository extends CrudRepository<Author,Long> {

    /**
     * Finds authors by partial or full name.
     *
     * @param authorName Partial or full name of the author.
     * @return List of authors whose name contains the input value.
     */
    @Query("SELECT a FROM Author a WHERE LOWER(CONCAT(a.firstName, ' ', a.lastName)) LIKE %:authorName%")
    List<Author> findAuthorsByNameContaining(String authorName);

    /**
     * Finds an author by full name.
     *
     * @param authorName Full name of the author.
     * @return The author with the specified full name or null if the author is not found.
     */
    @Query("SELECT a FROM Author a WHERE LOWER(CONCAT(a.firstName, ' ', a.lastName)) = :authorName")
    Author findAuthorsByFullName(String authorName);
}
