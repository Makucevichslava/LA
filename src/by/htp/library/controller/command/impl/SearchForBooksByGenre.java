package by.htp.library.controller.command.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.SearchService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class SearchForBooksByGenre implements Command {

	private static final String PARAM_DELIMETER_GENRE = "genre";
	private static final String BOOKS_REQUEST_GENRE_EMPTY = "Книг по запросу (жанр) не найдено.";
	private static final String BOOKS_REQUEST_GENRE = "Книги по запросу (жанр):";
	private static final String DELIMETER = " - ";
	private static final String GENRE_NULL = "Genre cann't be null";

	@Override
	public String execute(String request) {

		String genre = null;
		String response = "";

		// плюс цифра - количество букв в слове (после которого "вырезается" часть
		// строки) и знак равно

		genre = request.substring(request.indexOf(PARAM_DELIMETER_GENRE) + 6);

		if (genre == null || genre.isEmpty()) {
			return GENRE_NULL;
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SearchService searchService = serviceProvider.getSearchService();

		try {

			List<Book> books = searchService.searchForBooksByGenre(genre);

			if (books.isEmpty()) {

				response = BOOKS_REQUEST_GENRE_EMPTY;

			} else {

				System.out.println("\t" + BOOKS_REQUEST_GENRE);
				for (Book book : books) {
					response = response + book.getAuthor() + DELIMETER + book.getTitle() + DELIMETER + book.getGenre()
							+ DELIMETER + book.getYearOfPublishing() + DELIMETER + book.getType() + DELIMETER
							+ book.getAccessLevel() + DELIMETER + book.getAvailability() + "\n";
				}

			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return response;
	}

}
