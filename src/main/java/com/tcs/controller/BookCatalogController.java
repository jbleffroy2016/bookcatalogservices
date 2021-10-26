package com.tcs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.model.BookVO;
import com.tcs.service.BookCatalogService;

@RestController
public class BookCatalogController {
	
	@Autowired
	private BookCatalogService studentService;
	
	@RequestMapping(value = "/bookcatalog/getBooks", method = RequestMethod.GET)
	public ResponseEntity<List<BookVO>> getBooks(@RequestParam(name = "title",  required = false) String title,
			@RequestParam(name = "author",  required = false) String author,
			@RequestParam(name = "isbn",  required = false) String isbn,
			@RequestParam(name = "publishDate",  required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) throws Exception{
				return new ResponseEntity<List<BookVO>>(studentService.getBooks(title, author, isbn, publishDate), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bookcatalog/insertBooks", method = RequestMethod.POST)
	public ResponseEntity<String> insertBooks(@RequestBody List<BookVO> books) throws Exception{
		studentService.insertBooks(books);
		return new ResponseEntity<String>("Insert Successsful", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bookcatalog/updateBook", method = RequestMethod.POST)
	public ResponseEntity<String> updateBook(@RequestBody BookVO book) throws Exception{
		studentService.updateBook(book);
		return new ResponseEntity<String>("Update Successsful", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bookcatalog/deleteBooks", method = RequestMethod.POST)
	public ResponseEntity<String> deletBooks(@RequestBody List<String> isbn) throws Exception{
		studentService.deleteBook(isbn);
		return new ResponseEntity<String>("Delete Successsful", HttpStatus.OK);
	}
}
