package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();
    List<Book> findByGenreAndAgeGroup(String genre, String ageGroup);
}
