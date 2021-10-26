package com.tcs.dao;

import java.time.LocalDate;
import java.util.List;

import com.tcs.model.BookVO;

public interface BookCatalogDAO {
	
	public List<BookVO> getBooks(String title, String authors, String isbn, LocalDate publishDate) throws Exception;
	
	public int updateBook(BookVO book) throws Exception;
	
	public void deleteBook(List<String> isbn) throws Exception;
	
	public void insertBooks(List<BookVO> books) throws Exception;

}
