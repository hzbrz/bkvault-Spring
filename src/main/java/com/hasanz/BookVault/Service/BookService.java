package com.hasanz.BookVault.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.hasanz.BookVault.Repository.BookRepository;
import com.hasanz.BookVault.model.Book;

@Service
public class BookService {
    private final BookRepository bookRepository;

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BookService(BookRepository bookRepository, JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.bookRepository = bookRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // save for both because jdbc updates if id != null and creates if id == null
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    // public int countBooksByAuthor(String author) {
    // String sql = "SELECT COUNT(*) FROM books WHERE author = ?";
    // return jdbcTemplate.queryForObject(sql, Integer.class, author);
    // }

    public Book findBookTitleById(Long id) {
        String sql = "SELECT * FROM books where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public List<Book> searchBooks(String titleKeyword, String authorKeyword, BigDecimal maxPrice) {
        StringBuilder sql = new StringBuilder("SELECT * FROM books where 1=1");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (titleKeyword != null && !titleKeyword.isEmpty()) {
            sql.append(" AND title LIKE :title");
            params.addValue("title", "%" + titleKeyword + "%");
        }

        // if (authorKeyword != null && !authorKeyword.isEmpty()) {
        // sql.append(" AND author LIKE :author");
        // params.addValue("author", "%" + authorKeyword + "%");
        // }

        if (maxPrice != null) {
            sql.append(" AND price <= :maxPrice");
            params.addValue("maxPrice", maxPrice);
        }

        return namedParameterJdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Book.class));
    }
}
