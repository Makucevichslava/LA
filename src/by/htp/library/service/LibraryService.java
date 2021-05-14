package by.htp.library.service;

import by.htp.library.bean.Book;
import by.htp.library.service.exception.ServiceException;

public interface LibraryService {
	
	String addNewBook(Book book) throws ServiceException;
	
	String passToFriend(Book book, String friend) throws ServiceException;

	String returnBook(String title) throws ServiceException;

	String deleteBook(String id) throws ServiceException; 

}
