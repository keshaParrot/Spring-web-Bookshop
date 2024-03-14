package com.DenysiukProg.spring6webapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<UserEntity> userEntities = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id == book.id;
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public String getStringPublicationDate() {
        if(publicationDate==null) return null;
        return new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).format(publicationDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", numberOfPages='" + numberOfPages + '\'' +
                ", publicationDate=" + publicationDate +
                ", language='" + language + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", genre='" + genre + '\'' +
                ", authors=" + authors +
                ", userEntities=" + userEntities +
                ", publisher=" + publisher.getPublisherName() +
                '}';
    }
}
