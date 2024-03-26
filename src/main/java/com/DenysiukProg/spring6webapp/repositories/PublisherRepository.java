package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
    @Query("SELECT p FROM Publisher p WHERE LOWER(p.publisherName) LIKE %:publisherName%")
    List<Publisher> findAuthorsByNameContaining(String publisherName);
    @Query("SELECT p FROM Publisher p WHERE LOWER(p.publisherName) = :publisherName")
    Publisher findAuthorsByPublisherName(String publisherName);
}
