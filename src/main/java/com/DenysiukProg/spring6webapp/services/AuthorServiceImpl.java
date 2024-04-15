package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.repositories.AuthorRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing authors in the repository.
 * Implements business logic associated with book data.
 */
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    /**
     * Method that searches for author by first name and last name
     *
     * @return string type of the number of all authors in the database
     */
    @Override
    public String count(){
        return String.valueOf(authorRepository.count());
    }
    /**
     * Method that searches for author by first name and last name
     *
     * @return List of all authors in the database
     */
    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
    /**
     * Method that searches for author by first name and last name
     *
     * @param authorName author's name
     * @return author in the database
     */
    @Override
    public Author findAuthorsByFullName(String authorName){
        return authorRepository.findAuthorsByFullName(authorName);
    }

    /**
     * Method that searches for authors by first name and last name
     *
     * @param authorName author's name
     * @return List of authors in the database
     */
    @Override
    public List<Author> findAuthorsByNameContaining(String authorName) {
        return authorRepository.findAuthorsByNameContaining(authorName);
    }
}
