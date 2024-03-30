package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.repositories.PublisherRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    @Override
    public String count(){
        return String.valueOf(publisherRepository.count());
    }
    @Override
    public Iterable<Publisher> findAll() {
        return publisherRepository.findAll();
    }
    @Override
    public List<Publisher> findPublishersByNameContaining(String publisherName) {
        return publisherRepository.findAuthorsByNameContaining(publisherName);
    }
    @Override
    public Publisher findPublisherByPublisherName(String publisherName){
        return publisherRepository.findAuthorsByPublisherName(publisherName);
    }
}
