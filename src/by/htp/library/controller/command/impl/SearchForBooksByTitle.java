package by.htp.library.controller.command.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.SearchService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class SearchForBooksByTitle implements Command {

	private static final String PARAM_DELIMETER_TITLE = "title";
	private static final String BOOKS_REQUEST_TITLE_EMPTY = "Книг по запросу (название) не найдено.";
	private static final String BOOKS_REQUEST_TITLE = "Книги по запросу (название):";
	private static final String DELIMETER = " - ";
	private static final String TITLE_NULL = "Title cann't be null";

	@Override
	public String execute(String request) {

		String title = null;
		String response = "";

		// плюс цифра - количество букв в слове (после которого "вырезается" часть
		// строки) и знак равно

		title = request.substring(request.indexOf(PARAM_DELIMETER_TITLE) + 6);

		if (title == null || title.isEmpty()) {
			return TITLE_NULL;
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SearchService searchService = serviceProvider.getSearchService();

		try {

			List<Book> books = searchService.searchForBooksByTitle(title);

			if (books.isEmpty()) {

				response = BOOKS_REQUEST_TITLE_EMPTY;

			} else {

				System.out.println("\t" + BOOKS_REQUEST_TITLE);
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
