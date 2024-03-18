package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    String count();

    Iterable<Author> findAll();
}
