CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE Table books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    publisher VARCHAR(255),
    price DECIMAL(10,2),
    publishedDate DATE,
    author_id BIGINT,
    CONSTRAINT FK_books_author 
    FOREIGN KEY (author_id)
    REFERENCES authors(id)
);

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT,
    comment VARCHAR(255) NOT NULL,
    CONSTRAINT FK_reviews_book 
    FOREIGN KEY (book_id) 
    REFERENCES books(id)
);