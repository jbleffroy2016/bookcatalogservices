package com.tcs.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tcs.dao.BookCatalogDAO;
import com.tcs.model.BookVO;
import com.tcs.service.BookCatalogService;

@Service
public class BookCatalogServiceImpl implements BookCatalogService{
	
	@Autowired
	BookCatalogDAO dao;
	
	public List<BookVO> getBooks(String title, String authors, String isbn, LocalDate publishDate) throws Exception {
		List<BookVO> result = dao.getBooks(title, authors, isbn, publishDate);
		return result;
	}

	public int updateBook(BookVO book) throws Exception {
		if(StringUtils.isEmpty(book.getIsbn())){
			throw new Exception("ISBN of book is mandatory, please specify");
		}
		
		return dao.updateBook(book);
	}

	public void deleteBook(List<String> isbn) throws Exception {
		if(CollectionUtils.isEmpty(isbn)){
			throw new Exception("Please specify atleast one ISBN");
		}
		dao.deleteBook(isbn);
	}

	public void insertBooks(List<BookVO> books) throws Exception {
		if(CollectionUtils.isEmpty(books)) {
			throw new Exception("Please specify atleast one book detail");
		}
		dao.insertBooks(books);
	}

}
