# bookcatalogservices

http://localhost:8080/bookcatalogservices/h2-console

username: tcs

password: tcstest

select * from bookcatalog;

select * from auditlog;

http://localhost:8080/bookcatalogservices/bookcatalog/insertBooks


[{
  "title": "My Book",
  "authors":["Benjamin Leffroy"],
  "isbn": "3211234561234",
  "publishDate": "2021-10-26"
}]

http://localhost:8080/bookcatalogservices/bookcatalog/updateBook


{
  "title": "My Book Updated",
  "authors":["Benjamin Leffroy"],
  "isbn": "3211234561234",
  "publishDate": "2021-10-26"
}

http://localhost:8080/bookcatalogservices/bookcatalog/deleteBooks


[
"3211234561234"
]


http://localhost:8080/bookcatalogservices/bookcatalog/getBooks?title=Harry Potter&author=J.K.Rowling&isbn=1234567891234&publishDate=2021-10-26
