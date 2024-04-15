package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for working with Books table on database.
 */
public interface BookRepository extends JpaRepository<Book,Long> {

    /**
     * Retrieves all distinct genres of books.
     *
     * @return List of distinct genres.
     */
    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findAllGenres();

    /**
     * Finds books by genre, age group, and not by a specified ID.
     *
     * @param genre       The genre of the book.
     * @param ageGroup    The age group for which the book is suitable.
     * @param idToIgnore  The ID of the book to ignore.
     * @return List of books matching the criteria.
     */
    List<Book> findByGenreAndAgeGroupAndIdNot(String genre, String ageGroup, Long idToIgnore);

    /**
     * Finds the minimum price among all books.
     *
     * @return The minimum price found, wrapped in an Optional.
     */
    @Query("SELECT MIN(b.price) FROM Book b")
    Optional<Integer> findMinPrice();

    /**
     * Finds the maximum price among all books.
     *
     * @return The maximum price found, wrapped in an Optional.
     */
    @Query("SELECT MAX(b.price) FROM Book b")
    Optional<Integer> findMaxPrice();

    /**
     * Finds the books by genre.
     *
     * @return The List of books.
     */
    List<Book> findByGenre(String genre);

    /**
     * Finds books matching the specified criteria.
     *
     * @param searchTerm  The term to search in book titles.
     * @param minPrice    The minimum price of books.
     * @param maxPrice    The maximum price of books.
     * @param genres      The list of genres to filter by.
     * @return List of books matching the criteria.
     */
    @Query("SELECT b FROM Book b " +
            "WHERE (:searchTerm IS NULL OR LOWER(b.title) LIKE %:searchTerm%) " +
            "AND (:minPrice IS NULL OR b.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR b.price <= :maxPrice) " +
            "AND (:genres IS NULL OR b.genre IN :genres)")
    List<Book> findWithFilters(String searchTerm, Double minPrice, Double maxPrice, List<String> genres);
}
