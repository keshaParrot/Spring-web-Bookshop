package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Iterable<Book> findAll();

}
