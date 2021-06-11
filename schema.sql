DROP SCHEMA book_store; 


CREATE SCHEMA IF NOT EXISTS book_store;
USE book_store;

CREATE TABLE IF NOT EXISTS book_store.publisher (
	name VARCHAR(100) NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
	PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS book_store.author(
	name VARCHAR(100) NOT NULL,
    PRIMARY KEY(name)
);

CREATE TABLE IF NOT EXISTS book_store.book (
	ISBN_number VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL,
	author_name VARCHAR(100) NOT NULL,
	publisher_name VARCHAR(100) NOT NULL,
	publication_year year NOT NULL,
	selling_price INT NOT NULL,
	category VARCHAR(100) NOT NULL,
	number_copies INT NOT NULL,
	threshold INT NOT NULL,
    
	PRIMARY KEY (ISBN_number),
	CONSTRAINT publisher_name
    FOREIGN KEY (publisher_name)
    REFERENCES book_store.publisher (name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
    CONSTRAINT author_name
    FOREIGN KEY (author_name)
    REFERENCES book_store.author (name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS book_store.book_order (
	number INT NOT NULL,
    ISBN_number VARCHAR(100) NOT NULL,
	number_copies INT NOT NULL,
    date DATE NOT NULL,
    
	PRIMARY KEY (number),
	CONSTRAINT ISBN_number
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS book_store.user(
	user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    shipping_address VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_name)
);

CREATE TABLE IF NOT EXISTS book_store.cart(
	number INT NOT NULL,
    ISBN_number VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    number_copies INT NOT NULL,
    total_price INT,
    PRIMARY KEY (number),
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (user_name)
    REFERENCES book_store.user (user_name)
);

Create table if not exists book_store.sold_book(
	ISBN_number varchar(100) NOT NULL,
    selling_date date,
    sold_copies varchar(100),
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);