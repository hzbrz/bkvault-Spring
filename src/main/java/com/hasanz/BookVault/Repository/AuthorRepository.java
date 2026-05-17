package com.hasanz.BookVault.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hasanz.BookVault.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);

    @EntityGraph("Author.books")
    @Query("SELECT a FROM Author a")
    List<Author> findAllWithBooks();
}
