package com.tcs.model;

import java.time.LocalDate;
import java.util.List;

public class BookVO {
	
	private String title;
	
	private List<String> authors;
	
	private String isbn;
	
	private LocalDate publishDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "BookVO [title=" + title + ", authors=" + authors + ", isbn=" + isbn + ", publishDate=" + publishDate
				+ "]";
	}
	
}
