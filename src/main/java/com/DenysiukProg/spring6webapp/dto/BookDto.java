package com.DenysiukProg.spring6webapp.dto;

import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import com.DenysiukProg.spring6webapp.domain.Review;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
public class BookDto {

    private long id;
    private String title;
    private String isbn;
    private Date publicationDate;

    private String language;
    private String photoURL;
    private String description;
    private String price;
    private String genre;

    private Set<Review> reviews = new HashSet<>();
    private Set<Author> authorIds = new HashSet<>();
    private Publisher publisherId;
}
