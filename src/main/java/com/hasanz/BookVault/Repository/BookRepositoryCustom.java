package com.hasanz.BookVault.Repository;

import java.time.LocalDate;
import java.util.List;

import com.hasanz.BookVault.model.Book;

public interface BookRepositoryCustom {
    List<Book> findBooksByPublisherAndDateRange(
            String publisher,
            LocalDate start,
            LocalDate end);
}
