package com.hasanz.BookVault;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hasanz.BookVault.Service.BookstoreService;

@SpringBootApplication
public class BookVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookVaultApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BookstoreService bookstoreService) {
		return args -> {
			bookstoreService.createAuthorWithBooks();

			bookstoreService.findAllAuthorsAndTheirBooks();

			bookstoreService.printBookViewReport();
		};
	}
}
