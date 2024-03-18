package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.repositories.AuthorRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public String count(){
        return String.valueOf(authorRepository.count());
    }
    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
