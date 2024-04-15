package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for working with Publisher table on database.
 */
public interface PublisherRepository extends CrudRepository<Publisher,Long> {

    /**
     * Finds publishers by partial or full name.
     *
     * @param publisherName Partial or full name of the publisher.
     * @return List of publishers whose name contains the input value.
     */
    @Query("SELECT p FROM Publisher p WHERE LOWER(p.publisherName) LIKE %:publisherName%")
    List<Publisher> findAuthorsByNameContaining(String publisherName);

    /**
     * Finds a publisher by full name.
     *
     * @param publisherName Full name of the publisher.
     * @return The publisher with the specified full name or null if the publisher is not found.
     */
    @Query("SELECT p FROM Publisher p WHERE LOWER(p.publisherName) = :publisherName")
    Publisher findAuthorsByPublisherName(String publisherName);
}
