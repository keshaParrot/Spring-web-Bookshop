package com.DenysiukProg.spring6webapp.domain.dto;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.domain.entity.UserOrder;
import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.domain.entity.Review;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "Book title should not be empty")
    private String title;
    @NotEmpty(message = "Book ISBN should not be empty")
    private String isbn;

    @NotEmpty(message = "Book age group should not be empty")
    private String ageGroup;
    @NotEmpty(message = "Book number of pages should not be empty")
    private String numberOfPages;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
    @NotEmpty(message = "Book language should not be empty")
    private String language;
    @NotEmpty(message = "Book photo should not be empty")
    private String photoURL;
    @Min(value = 1, message = "Book price should be greater than zero")
    private int price;
    @NotEmpty(message = "Book genre should not be empty")
    private String genre;

    @NotEmpty(message = "First description paragraph should not be empty")
    private String descriptionParagraph1;
    @NotEmpty(message = "Second description paragraph should not be empty")
    private String descriptionParagraph2;
    private String descriptionParagraph3;

    private Set<Review> reviews = new HashSet<>();
    @NotEmpty(message = "Book need to have author")
    private Set<Author> authors = new HashSet<>();
    private Set<UserOrder> orders = new HashSet<>();
    @NotNull(message = "Book need to have publisher")
    private Publisher publisher;

    public String getStringPublicationDate() {
        if(publicationDate==null) return null;
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(publicationDate);
    }
}
