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
	price INT NOT NULL,
	category VARCHAR(100) NOT NULL,
	number_copies INT NOT NULL,
	threshold INT NOT NULL,

	PRIMARY KEY (ISBN_number),
    FOREIGN KEY (publisher_name)
    REFERENCES book_store.publisher (name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    FOREIGN KEY (author_name)
    REFERENCES book_store.author (name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS book_store.book_order (
	number INT NOT NULL,
    ISBN_number VARCHAR(100) NOT NULL,
	number_copies INT NOT NULL,

	PRIMARY KEY (number),
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

DELIMITER $$

CREATE TRIGGER not_negative
BEFORE UPDATE ON book_store.book
FOR EACH ROW
BEGIN
	IF NEW.number_copies < 0 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'The number of copies mustn\'t be negative',
        MYSQL_ERRNO = 1001;
	END IF;
END $$

DELIMITER $$

CREATE TRIGGER place_order
AFTER UPDATE ON book_store.book
FOR EACH ROW
BEGIN
	IF NEW.number_copies < OLD.threshold THEN
		SIGNAL SQLSTATE '01000'
        SET MESSAGE_TEXT = 'Number of copies is less than the threshold',
        MYSQL_ERRNO = 1000;
	END IF;
END $$

DELIMITER ;

CREATE TRIGGER confirm_order
BEFORE DELETE ON book_store.book_order
FOR EACH ROW
SET @book_store.number_copies = @book_store.number_copies + OLD.number_copies;

CREATE TABLE IF NOT EXISTS book_store.user(
	user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    shipping_address VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_name)
);

CREATE TABLE IF NOT EXISTS book_store.cart_item(
	number INT NOT NULL,
    ISBN_number VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    number_copies INT NOT NULL,

    PRIMARY KEY (number),
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (user_name)
    REFERENCES book_store.user (user_name)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

Create table if not exists book_store.sold_book(
	number VARCHAR(100) NOT NULL,
    ISBN_number VARCHAR(100),
    user_name VARCHAR(100),
    date DATE NOT NULL,
    number_copies INT NOT NULL,
    price INT NOT NULL,

    PRIMARY KEY (number),
    FOREIGN KEY (ISBN_number)
    REFERENCES book_store.book (ISBN_number)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
    FOREIGN KEY (user_name)
    REFERENCES book_store.user (user_name)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);