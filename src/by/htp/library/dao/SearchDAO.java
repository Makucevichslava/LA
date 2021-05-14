package by.htp.library.dao;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;

public interface SearchDAO {

	List<Book> searchForBooksByGenre(String genre) throws DAOException;

	List<Book> searchForBooksByTitle(String title) throws DAOException;

	List<Book> searchForBooksByAuthor(String author) throws DAOException;

}
