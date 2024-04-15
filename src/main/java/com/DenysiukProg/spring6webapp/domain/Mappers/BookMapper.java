package com.DenysiukProg.spring6webapp.domain.Mappers;

import com.DenysiukProg.spring6webapp.domain.dto.BookDto;
import com.DenysiukProg.spring6webapp.domain.entity.Book;

public class BookMapper {

    public static BookDto entityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationDate(book.getPublicationDate())
                .ageGroup(book.getAgeGroup())
                .numberOfPages(book.getNumberOfPages())
                .language(book.getLanguage())
                .photoURL(book.getPhotoURL())
                .descriptionParagraph1(book.getDescriptionParagraph1())
                .descriptionParagraph2(book.getDescriptionParagraph2())
                .descriptionParagraph3(book.getDescriptionParagraph3())
                .price(book.getPrice())
                .genre(book.getGenre())
                .authors(book.getAuthors())
                .publisher(book.getPublisher())
                .build();


    }
    public static Book DtoToEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .publicationDate(bookDto.getPublicationDate())
                .ageGroup(bookDto.getAgeGroup())
                .numberOfPages(bookDto.getNumberOfPages())
                .language(bookDto.getLanguage())
                .photoURL(bookDto.getPhotoURL())
                .descriptionParagraph1(bookDto.getDescriptionParagraph1())
                .descriptionParagraph2(bookDto.getDescriptionParagraph2())
                .descriptionParagraph3(bookDto.getDescriptionParagraph3())
                .price(bookDto.getPrice())
                .genre(bookDto.getGenre())
                .authors(bookDto.getAuthors())
                .publisher(bookDto.getPublisher())
                .build();
    }
}
