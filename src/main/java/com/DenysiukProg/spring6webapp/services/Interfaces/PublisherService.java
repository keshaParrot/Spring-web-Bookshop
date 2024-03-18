package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Publisher;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService {
    String count();

    Iterable<Publisher> findAll();
}
