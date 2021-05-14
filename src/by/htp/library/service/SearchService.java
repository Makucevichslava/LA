package by.htp.library.service;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

public interface SearchService {

	List<Book> searchForBooksByGenre(String genre) throws ServiceException;

	List<Book> searchForBooksByTitle(String title) throws ServiceException;

	List<Book> searchForBooksByAuthor(String author) throws ServiceException;

}
