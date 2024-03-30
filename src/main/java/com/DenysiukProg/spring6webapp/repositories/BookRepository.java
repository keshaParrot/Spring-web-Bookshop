package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();
    List<Book> findByGenreAndAgeGroupAndIdNot(String genre, String ageGroup, Long idToIgnore);
    @Query("SELECT MIN(b.price) FROM Book b")
    Optional<Integer> findMinPrice();

    @Query("SELECT MAX(b.price) FROM Book b")
    Optional<Integer> findMaxPrice();
    List<Book> findByGenre(String genre);
    @Query("SELECT b FROM Book b " +
            "WHERE (:searchTerm IS NULL OR LOWER(b.title) LIKE %:searchTerm%) " +
            "AND (:minPrice IS NULL OR b.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR b.price <= :maxPrice) " +
            "AND (:genres IS NULL OR b.genre IN :genres)")
    List<Book> findWithFilters(String searchTerm, Double minPrice, Double maxPrice, List<String> genres);
}
