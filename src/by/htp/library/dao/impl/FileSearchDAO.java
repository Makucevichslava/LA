package by.htp.library.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.SearchDAO;
import by.htp.library.dao.exception.DAOException;

public class FileSearchDAO implements SearchDAO {

	private static final String SOURSE_PATH = "resources/Books.txt";
	private static final String PARAM_DELIMETER = "/";

	private static final int AUTHOR_LOCATION = 1;
	private static final int TITLE_LOCATION = 2;
	private static final int GENRE_LOCATION = 3;
	private static final int YEAR_PUBLISHING_LOCATION = 4;
	private static final int TYPE_LOCATION = 5;
	private static final int ACCESS_LOCATION = 6;
	private static final int AVAILABILITY_LOCATION = 7;

	@Override
	public List<Book> searchForBooksByGenre(String genre) throws DAOException {

		File booksDataSourse = new File(SOURSE_PATH);
		BufferedReader reader = null;

		List<Book> books = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				String[] arrayBookData = findSuitableBooksByGenre(bookData, genre);
				if (arrayBookData == null) {
					continue;

				} else {
					books.add(new Book(arrayBookData[AUTHOR_LOCATION], arrayBookData[TITLE_LOCATION],
							arrayBookData[GENRE_LOCATION], arrayBookData[YEAR_PUBLISHING_LOCATION],
							arrayBookData[TYPE_LOCATION], arrayBookData[ACCESS_LOCATION],
							arrayBookData[AVAILABILITY_LOCATION]));
				}
			}

			if (books.isEmpty()) {
				return books = Collections.emptyList();
			} else {
				return books;
			}

		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	@Override
	public List<Book> searchForBooksByTitle(String title) throws DAOException {

		File booksDataSourse = new File(SOURSE_PATH);
		BufferedReader reader = null;

		List<Book> books = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				String[] arrayBookData = findSuitableBooksByTitle(bookData, title);
				if (arrayBookData == null) {
					continue;

				} else {
					books.add(new Book(arrayBookData[AUTHOR_LOCATION], arrayBookData[TITLE_LOCATION],
							arrayBookData[GENRE_LOCATION], arrayBookData[YEAR_PUBLISHING_LOCATION],
							arrayBookData[TYPE_LOCATION], arrayBookData[ACCESS_LOCATION],
							arrayBookData[AVAILABILITY_LOCATION]));
				}
			}

			if (books.isEmpty()) {
				return books = Collections.emptyList();
			} else {
				return books;
			}

		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	@Override
	public List<Book> searchForBooksByAuthor(String author) throws DAOException {

		File booksDataSourse = new File(SOURSE_PATH);
		BufferedReader reader = null;

		List<Book> books = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				String[] arrayBookData = findSuitableBooksByAuthor(bookData, author);

				if (arrayBookData == null) {
					continue;

				} else {

					books.add(new Book(arrayBookData[AUTHOR_LOCATION], arrayBookData[TITLE_LOCATION],
							arrayBookData[GENRE_LOCATION], arrayBookData[YEAR_PUBLISHING_LOCATION],
							arrayBookData[TYPE_LOCATION], arrayBookData[ACCESS_LOCATION],
							arrayBookData[AVAILABILITY_LOCATION]));
				}
			}

			if (books.isEmpty()) {
				return books = Collections.emptyList();
			} else {
				return books;
			}

		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	private String[] findSuitableBooksByAuthor(String bookData, String author) {

		String[] arrayBookData;
		String temp;

		arrayBookData = bookData.split(PARAM_DELIMETER);
		temp = arrayBookData[AUTHOR_LOCATION];

		if (author.equals(temp)) {
			return arrayBookData;
		} else {
			return null;
		}

	}

	private String[] findSuitableBooksByGenre(String bookData, String genre) {

		String[] arrayBookData;
		String temp;

		arrayBookData = bookData.split(PARAM_DELIMETER);
		temp = arrayBookData[GENRE_LOCATION];

		if (genre.equals(temp)) {
			return arrayBookData;
		} else {
			return null;
		}

	}

	private String[] findSuitableBooksByTitle(String bookData, String title) {

		String[] arrayBookData;
		String temp;

		arrayBookData = bookData.split(PARAM_DELIMETER);
		temp = arrayBookData[TITLE_LOCATION];

		if (title.equals(temp)) {
			return arrayBookData;
		} else {
			return null;
		}

	}

}
