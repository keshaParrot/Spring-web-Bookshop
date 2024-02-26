package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();

}
