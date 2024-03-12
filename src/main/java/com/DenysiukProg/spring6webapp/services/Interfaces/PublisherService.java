package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService {
    Iterable<Publisher> findAll();
}
