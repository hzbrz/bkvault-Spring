package com.hasanz.BookVault;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hasanz.BookVault.Service.BookService;
import com.hasanz.BookVault.model.Book;

@SpringBootApplication
public class BookVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookVaultApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(BookService bookService) {
	// return args -> {
	// System.out.println("=== RUNNING COMMANDLINERUNNER TESTS ===");

	// Book b1 = new Book();
	// b1.setTitle("Clean Code");
	// b1.setAuthor("Robert Martin");
	// b1.setIsbn("ISBN-1");
	// b1.setPrice(new BigDecimal(30));
	// b1.setpublishedDate(LocalDate.of(2017, 9, 20));

	// Book b2 = new Book();
	// b2.setTitle("Clean Architecture");
	// b2.setAuthor("Robert Martin");
	// b2.setIsbn("ISBN-2");
	// b2.setPrice(new BigDecimal("40"));
	// b2.setpublishedDate(LocalDate.of(2017, 9, 20));

	// bookService.addBook(b1);
	// bookService.addBook(b2);

	// b1.setPrice(new BigDecimal(12.20));
	// bookService.updateBook(b1);

	// System.out.println(bookService.countBooksByAuthor("Robert Martin"));

	// System.out.println(bookService.findBookTitleById(1L));

	// System.out.println("\n=== SEARCHES ===");
	// bookService.searchBooks("Clean", null, null).forEach(System.out::println);

	// System.out.println(bookService.searchBooks(null, "Robert Martin", null));

	// System.out.println(bookService.searchBooks(null, null, new BigDecimal(30)));
	// };
	// }
}
