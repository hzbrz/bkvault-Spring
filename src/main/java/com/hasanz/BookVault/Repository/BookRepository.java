package com.hasanz.BookVault.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hasanz.BookVault.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.reviews WHERE b.id = :bookId")
    Optional<Book> findBookWithReviews(@Param("bookId") Long bookId);

    List<Book> findByAuthorId(Long authorId);
}
