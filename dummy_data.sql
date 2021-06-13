insert into publisher(name, address, phone_number)
values("dummy publisher" , "address" , "012234234324") ;

insert into publisher(name , address , phone_number)
values("publisher1" , "address2" , "012312421") ;


insert into book(isbn_number, title, author_name, publisher_name, publication_year, price, category, number_copies, threshold)
values ("12" , "title1" , "author1" , "publisher1" , "2010" , 120 , "art" , 100 , 80) ;


insert into book(isbn_number, title, author_name, publisher_name, publication_year, price, category, number_copies, threshold)
values ("13" , "title2" , "author2" , "publisher1" , "2010" , 120 , "art" , 100 , 80) ;


insert into book(isbn_number, title, author_name, publisher_name, publication_year, price, category, number_copies, threshold)
values ("14" , "title3" , "author3" , "dummy publisher" , "2010" , 120 , "art" , 100 , 80) ;


insert into book(isbn_number, title, author_name, publisher_name, publication_year, price, category, number_copies, threshold)
values ("15" , "title4" , "author3" , "dummy publisher" , "2010" , 120 , "art" , 100 , 80) ;