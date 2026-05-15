package com.hasanz.BookVault.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hasanz.BookVault.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksByPublisherAndDateRange(String publisher, LocalDate start, LocalDate end) {
        String jpql = "SELECT b from Book b WHERE b.publisher = :publisher AND b.publishedDate BETWEEN :start and :end";

        return entityManager.createQuery(jpql, Book.class)
                .setParameter("publisher", publisher)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
}
