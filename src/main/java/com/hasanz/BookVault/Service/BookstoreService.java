package com.hasanz.BookVault.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hasanz.BookVault.DTO.BookReviewReportDto;
import com.hasanz.BookVault.Repository.AuthorRepository;
import com.hasanz.BookVault.model.Author;
import com.hasanz.BookVault.model.Book;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final JdbcTemplate jdbcTemplate;

    public BookstoreService(AuthorRepository authorRepository,
            JdbcTemplate jdbcTemplate) {
        this.authorRepository = authorRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> createAuthorWithBooks() {
        Author author1 = new Author();
        author1.setName("George Orwell");

        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setIsbn("ISBN-1");
        book1.setPrice(new BigDecimal("30"));
        book1.setPublisher("Hasan's Books");
        book1.setPublishedDate(LocalDate.of(2018, 9, 20));
        book1.setAuthor(author1);

        Author author2 = new Author();
        author2.setName("Test author 2");
        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setIsbn("ISBN-2");
        book2.setPrice(new BigDecimal("10.12"));
        book2.setPublisher("Hasan's Books");
        book2.setPublishedDate(LocalDate.of(2012, 11, 1));
        book2.setAuthor(author2);

        author1.getBooks().add(book1);
        author2.getBooks().add(book2);

        return authorRepository.saveAll(List.of(author1, author2));
    }

    public void findAllAuthorsAndTheirBooks() {
        // this would cause a N! problem upon fetching all the related books
        // List<Author> authors = authorRepository.findAll();

        // Entity graph solution
        List<Author> authors = authorRepository.findAllWithBooks();

        for (Author author : authors) {
            System.out.println("Author: " + author.getName());
            System.out.println("Number of books: " + author.getBooks().size());
        }
    }

    // performance optimized aggregate reporting method
    public List<BookReviewReportDto> getTotalReviewsByBookReport() {
        String sql = """
                    SELECT b.title, COUNT(r.id) as review_count
                    FROM books b LEFT JOIN reviews r ON b.id = r.book_id
                    GROUP BY b.id, b.title ORDER BY review_count DESC
                """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new BookReviewReportDto(rs.getString("title"), rs.getInt("review_count")));
    }

    public void printBookViewReport() {
        List<BookReviewReportDto> report = getTotalReviewsByBookReport();

        System.out.println("\n=== BOOK REVIEW REPORT ===");

        for (BookReviewReportDto row : report) {
            System.out.println(row.getTitle() + "->" + row.getReviewCount());
        }
    }
}
