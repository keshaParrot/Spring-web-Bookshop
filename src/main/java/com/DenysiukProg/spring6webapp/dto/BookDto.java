package com.DenysiukProg.spring6webapp.dto;

import com.DenysiukProg.spring6webapp.domain.Author;
import com.DenysiukProg.spring6webapp.domain.Publisher;
import com.DenysiukProg.spring6webapp.domain.Review;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Data
@Setter
@Getter
public class BookDto {

    private long id;
    private String title;
    private String isbn;

    private String ageGroup;
    private String numberOfPages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
    private String language;
    private String photoURL;
    private String description;
    private String price;
    private String genre;

    private Set<Review> reviews = new HashSet<>();
    private Set<Author> authorIds = new HashSet<>();
    private Set<UserEntity> userEntities = new HashSet<>();
    private Publisher publisherId;

    public String getStringPublicationDate() {
        if(publicationDate==null) return null;
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(publicationDate);
    }
}
