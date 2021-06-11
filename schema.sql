CREATE SCHEMA IF NOT EXISTS bookstore ;
USE bookstore ;

CREATE TABLE IF NOT EXISTS bookstore.PUBLISHER (
  Name VARCHAR(100) NOT NULL,
  Address VARCHAR(100) NOT NULL,
  Phonenumber CHAR(50) NOT NULL,
  PRIMARY KEY (Name));

CREATE TABLE IF NOT EXISTS bookstore.BOOK (
	ISBNnumber INT NOT NULL,
	Title VARCHAR(100) NOT NULL,
	Author VARCHAR(100) NOT NULL,
	PublisherName VARCHAR(100) NOT NULL,
	PublicationYear year NOT NULL,
	SellingPrice INT NOT NULL,
	Category VARCHAR(100) NOT NULL,
	Copies INT NOT NULL,
    
	threshold INT,
	PRIMARY KEY (ISBNnumber),
	CONSTRAINT publisher_name
    FOREIGN KEY (PublisherName)
    REFERENCES bookstore.PUBLISHER (Name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS bookstore.BookOrders (
  ISBNnumber INT NOT NULL,
  Copies INT NOT NULL,
  PRIMARY KEY (ISBNnumber),
  CONSTRAINT ISBN_number
    FOREIGN KEY (ISBNnumber)
    REFERENCES bookstore.BOOK (ISBNnumber)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

Create table if not exists bookstore.users(
	username varchar(100) not null,
    password varchar(100) not null,
    lastName varchar(100),
    firstName varchar(100),
    email varchar(100),
    phoneNumber char(50),
    shippingAddress varchar(100),
    status varchar(100),
    PRIMARY KEY (`username`)
    );

Create table if not exists bookstore.cart(
	ISBN INT not null,
    price INT not null,
    copies Int not null,
    totalPrice Int,
    FOREIGN KEY (ISBN)
    REFERENCES bookstore.BOOK (ISBNnumber)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

Create table if not exists bookstore.soldBooks(
	ISBN INT not null,
    sellingDate date,
    soldCopies varchar(100),
    FOREIGN KEY (ISBN)
    REFERENCES bookstore.BOOK (ISBNnumber)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)