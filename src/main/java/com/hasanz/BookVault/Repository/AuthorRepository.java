package com.hasanz.BookVault.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasanz.BookVault.model.Author;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);
}
