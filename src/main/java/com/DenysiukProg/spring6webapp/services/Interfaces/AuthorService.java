package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    String count();

    Iterable<Author> findAll();

    List<Author> findAuthorsByNameContaining(String authorName);
    Author findAuthorsByFullName(String fullName);
}
