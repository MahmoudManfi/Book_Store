-- # insert some stupid users
insert into user(user_name, password, last_name, first_name, email, phone_number, shipping_address, status)
values ("Hamza" , "hamzahamza" , "Hassan" , "Hamza" , "hamzahassa835@gmail.com" , "01284233227" , "bitash" , "Manager");

insert into user(user_name, password, last_name, first_name, email, phone_number, shipping_address, status)
values ("Mahmoud" , "mahmoudmahmoud" , "Manfy" , "Mahmoud" , "manfy@gmail.com" , "01234533227" , "bitash" , "user");

insert into user(user_name, password, last_name, first_name, email, phone_number, shipping_address, status)
values ("salama99" , "salamasalama" , "salama" , "ahmed" , "ahmedadelsalama@gmail.com" , "01278233227" , "somewhere" , "user");

insert into user(user_name, password, last_name, first_name, email, phone_number, shipping_address, status)
values ("saad" , "saadsaad" , "saad" , "Mohammed" , "mohammedsaad@gmail.com" , "01284233777" , "wrdian" , "user");

-- # insert some stupid orders
insert into sold_book(number, ISBN_number, user_name, date, number_copies, price)
values("1" , "12" , "Hamza" , "2021-06-12" , 10 , 150);

insert into sold_book(number, ISBN_number, user_name, date, number_copies, price)
values("2" , "13" , "Hamza" , "2021-05-14" , 20 , 150);

insert into sold_book(number, ISBN_number, user_name, date, number_copies, price)
values("3" , "12" , "mahmoud" , "2020-04-12" , 30 , 150);


insert into sold_book(number, ISBN_number, user_name, date, number_copies, price)
values("4" , "12" , "saad" , "2021-04-12" , 40 , 150);

update sold_book set date = "2021-06-12" where number = "1" ;
update sold_book set date = "2021-05-14" where number = "2" ;

-- #Query of the first report entry
select book.title, sum(sold_book.number_copies)
from book join sold_book on book.ISBN_number = sold_book.ISBN_number
where date >= ( now() - interval  1 month )
group by book.title;
