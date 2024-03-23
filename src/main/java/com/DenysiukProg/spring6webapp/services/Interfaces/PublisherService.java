package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {
    String count();

    Iterable<Publisher> findAll();


    List<Publisher> findPublishersByNameContaining(String publisherName);
}
