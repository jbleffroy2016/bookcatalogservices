package com.tcs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.dao.BookCatalogDAO;
import com.tcs.model.BookVO;

@Repository
public class BookCatalogDAOImpl implements BookCatalogDAO {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	class BookCatalogRowMapper implements ResultSetExtractor<List<BookVO>> {

		public List<BookVO> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<BookVO> list = new ArrayList<BookVO>();
			while (rs.next()) {
				BookVO domain = new BookVO();
				domain.setTitle(rs.getString("title"));
				domain.setAuthors(
						rs.getString("authors") != null ? Arrays.asList(rs.getString("authors").split(",")) : null);
				domain.setIsbn(rs.getString("isbn"));
				domain.setPublishDate(
						rs.getDate("publish_date") != null ? rs.getDate("publish_date").toLocalDate() : null);

				list.add(domain);
			}
			return list;
		}

	}

	public List<BookVO> getBooks(String title, String authors, String isbn, LocalDate publishDate) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select title, authors, isbn, publish_date from bookcatalog where 1=1 ");

		if (StringUtils.isNotEmpty(title)) {
			params.addValue("title", title);
			stringBuilder.append(" AND title = :title");
		}
		if (StringUtils.isNotEmpty(authors)) {
			params.addValue("authors", authors);
			stringBuilder.append(" AND authors = :authors");
		}
		if (StringUtils.isNotEmpty(isbn)) {
			params.addValue("isbn", isbn);
			stringBuilder.append(" AND isbn = :isbn");
		}
		if (null != publishDate) {
			params.addValue("publishDate", publishDate);
			stringBuilder.append(" AND publish_date = :publishDate");
		}

		List<BookVO> result = jdbcTemplate.query(stringBuilder.toString(), params, new BookCatalogRowMapper());

		return result;
	}

	public int updateBook(BookVO book) throws Exception {
		String sql = "update bookcatalog set title = :title, authors = :authors, publish_date = :publishDate where isbn = :isbn";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("title", book.getTitle());
		params.addValue("authors", StringUtils.join(book.getAuthors(), ','));
		params.addValue("publishDate", book.getPublishDate());
		params.addValue("isbn", book.getIsbn());

		int update = jdbcTemplate.update(sql, params);

		if (update > 0) {
			System.out.println("Update successful for ISBN :::" + book.getIsbn());
		}
		return update;
	}

	public void deleteBook(List<String> isbnList) throws Exception {
		for (String isbn : isbnList) {
			String sql = "delete from bookcatalog where isbn = :isbn";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("isbn", isbn);

			int delete = 0;
			try {
				delete = jdbcTemplate.update(sql, params);
			} catch (Exception e) {
				System.out.println("Error while deleting ISBN :::" + isbn);
			}

			if (delete > 0) {
				System.out.println("Delete successful for ISBN :::" + isbn);
			}

		}
	}

	public void insertBooks(List<BookVO> books) throws Exception {
		for (BookVO book : books) {
			String sql = "insert into bookcatalog (title, authors, isbn, publish_date) values (:title, :authors, :isbn, :publishDate )";
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("title", book.getTitle());
			params.addValue("authors", book.getAuthors());
			params.addValue("publishDate", book.getPublishDate());
			params.addValue("isbn", book.getIsbn());

			int insert = 0;
			try {
				insert = jdbcTemplate.update(sql, params);
			} catch (Exception e) {
				System.out.println("Error while updating ISBN :::" + book.getIsbn());
			}

			if (insert > 0) {
				System.out.println("Insert successful for ISBN :::" + book.getIsbn());
			}
		}
	}

}
