package by.htp.library.service.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.dao.DAOProvider;
import by.htp.library.dao.SearchDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.SearchService;
import by.htp.library.service.exception.ServiceException;

public class SearchServiceImpl implements SearchService {

	private static final String patternTitle = "(^[A-Z0-9])([^(а-яА-я)]){2,}";
	private static final String patternGenre = "(^[a-z])([^(а-яА-я)(0-9) ]){4,20}";
	private static final String patternAuthor = "(^[A-Z])([^(а-яА-я)]){4,}";

	@Override
	public List<Book> searchForBooksByGenre(String genre) throws ServiceException {

		// проверка жанра:
		// - только латинские символы
		// - нет цифр, пробелов, букв русского алфавита
		// - от 5 до 20 символов

		if (!genre.matches(patternGenre)) {
			throw new ServiceException("invalid genre format");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		SearchDAO searchDAO = daoProvider.getFileSearchImpl();

		List <Book> books;

		try {
			books = searchDAO.searchForBooksByGenre(genre);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return books;
	}

	@Override
	public List<Book> searchForBooksByTitle(String title) throws ServiceException {

		// проверка наименования:
		// - только латинские символы
		// - начинается с заглавной буквы или цифры
		// - минимум 2 символа

		if (!title.matches(patternTitle)) {
			throw new ServiceException("invalid title format");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		SearchDAO searchDAO = daoProvider.getFileSearchImpl();

		List <Book> books;

		try {
			books = searchDAO.searchForBooksByTitle(title);
		} catch (DAOException e) {

			throw new ServiceException(e);
		}

		return books;
	}

	@Override
	public List<Book> searchForBooksByAuthor(String author) throws ServiceException {

		// проверка автора:
		// - только латинские символы, с большой буквы
		// - нет цифр, букв русского алфавита
		// - от 5 символов

		if (!author.matches(patternAuthor)) {
			throw new ServiceException("invalid author format");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		SearchDAO searchDAO = daoProvider.getFileSearchImpl();

		List <Book> books;

		try {
			books = searchDAO.searchForBooksByAuthor(author);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return books;
	}

}
