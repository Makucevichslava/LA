package by.htp.library.dao;

import by.htp.library.bean.Book;
import by.htp.library.dao.exception.DAOException;

public interface BookDAO {

	void addBook(Book book) throws DAOException;

	void deleteBook(Book book) throws DAOException;

	boolean passToFriend(Book book, String friend) throws DAOException;

	boolean returnBook(String title) throws DAOException;

}
