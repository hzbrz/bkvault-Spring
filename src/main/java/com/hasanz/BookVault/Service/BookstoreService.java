package com.hasanz.BookVault.Service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hasanz.BookVault.Repository.AuthorRepository;
import com.hasanz.BookVault.Repository.BookRepository;
import com.hasanz.BookVault.model.Author;
import com.hasanz.BookVault.model.Book;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final JdbcTemplate jdbcTemplate;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository,
            JdbcTemplate jdbcTemplate) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Author createAuthorWithBooks() {
        Author author = new Author();

        author.setName("George Orwell");

        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setAuthor(author);

        author.getBooks().add(book1);
        author.getBooks().add(book2);

        return authorRepository.save(author);
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
}
