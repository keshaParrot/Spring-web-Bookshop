package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    Iterable<Author> findAll();
}
