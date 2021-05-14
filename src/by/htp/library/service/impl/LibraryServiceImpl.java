package by.htp.library.service.impl;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.DAOProvider;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.LibraryService;
import by.htp.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService {

	public static final DAOProvider daoProvider = DAOProvider.getInstance();
	public static final BookDAO bookDAO = daoProvider.getFileBookImpl();

	private static final String patternTitle = "(^[A-Z0-9])([^(а-яА-я)]){2,}";
	private static final String patternGenre = "(^[a-z])([^(а-яА-я)(0-9) ]){4,20}";
	private static final String patternAuthor = "(^[A-Z])([^(а-яА-я)]){4,}";
	private static final String patternFriend = "^([A-Z]{1}[a-z]{1,23}[\\s]{1}[A-Z]{1}[a-z]{1,23})";
	private static final String patternYear = "^[0-9]{3,4}$";
	private static final String patternType = "(^paper)$|(^electronic)$";
	private static final String patternAccessLevel = "(^general access)$|(^no baby)$";
	private static final String patternAvailability = "(^home)$";
	private static final String patternId = "^[1-9]\\d*$";

	@Override
	public String addNewBook(Book book) throws ServiceException {

		String author = book.getAuthor();
		String title = book.getTitle();
		String genre = book.getGenre();
		String yearOfPublishing = book.getYearOfPublishing();
		String type = book.getType();
		String accessLevel = book.getAccessLevel();
		String availability = book.getAvailability();

		if (!author.matches(patternAuthor)) {
			throw new ServiceException("invalid author format");
		}

		if (!title.matches(patternTitle)) {
			throw new ServiceException("invalid title format");
		}

		if (!genre.matches(patternGenre)) {
			throw new ServiceException("invalid genre format");
		}

		if (!yearOfPublishing.matches(patternYear)) {
			throw new ServiceException("invalid yearOfPublishing format");
		}

		if (!type.matches(patternType)) {
			throw new ServiceException("invalid type format");
		}

		if (!accessLevel.matches(patternAccessLevel)) {
			throw new ServiceException("invalid accessLevel format");
		}

		if (!availability.matches(patternAvailability)) {
			throw new ServiceException("invalid availability format");
		}

		String response = "";

		try {

			bookDAO.addBook(book);
			response = "The book added to the library.";

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return response;

	}

	@Override
	public String passToFriend(Book book, String friend) throws ServiceException {

		// проверка наименования:
		// - только латинские символы
		// - начинается с заглавной буквы или цифры
		// - минимум 2 символа

		String title = book.getTitle();
		
		if (!title.matches(patternTitle)) {
			throw new ServiceException("invalid title format");
		}

		// проверка имени друга:
		// - только латинские символы
		// - имя и фамилия начинаются с заглавной буквы
		// - один пробел между именем и фамилией

		if (!friend.matches(patternFriend)) {
			throw new ServiceException("invalid friend format");
		}

		String response = "";

		try {

			if (bookDAO.passToFriend(book, friend)) {
				response = "The book was passed to the friend.";
			} else {
				response = "The book is outside the home or there is no such book.";
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return response;

	}

	@Override
	public String returnBook(String title) throws ServiceException {

		if (!title.matches(patternTitle)) {

			throw new ServiceException("invalid title format");
		}

		String response = "";

		try {

			if (bookDAO.returnBook(title)) {
				response = "The book returned.";
			} else {
				response = "The book is at the home or there is no such book.";
			}

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

		return response;

	}

	@Override
	public String deleteBook(String id) throws ServiceException {

		if (!id.matches(patternId)) {

			throw new ServiceException("Invalid id format.");
		}
		
		int idBook = Integer.parseInt(id);
		
		Book book = new Book(idBook);
		
		String response = "";
		
		try {

			bookDAO.deleteBook(book);
			response = "The book deleted.";

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

		return response;
	}

}
