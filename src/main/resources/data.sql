create table bookcatalog
( 
   title varchar(255) not null,
   authors varchar(255) not null,
   isbn varchar(13) not null,
   publish_date DATE,
   primary key(isbn)
);

insert into bookcatalog (title, authors, isbn, publish_date)
values
('Harry Potter', 'J.K.Rowling', '1234567891234', '2021-10-26');