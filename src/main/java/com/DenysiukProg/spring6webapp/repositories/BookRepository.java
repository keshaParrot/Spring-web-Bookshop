package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();
    List<Book> findByGenreAndAgeGroupAndIdNot(String genre, String ageGroup, Long idToIgnore);
    @Query("SELECT MIN(b.price) FROM Book b")
    Optional<Double> findMinPrice();

    @Query("SELECT MAX(b.price) FROM Book b")
    Optional<Double> findMaxPrice();
    List<Book> findByGenre(String genre);
}
