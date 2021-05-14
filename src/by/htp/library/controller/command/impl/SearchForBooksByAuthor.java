package by.htp.library.controller.command.impl;

import java.util.List;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.SearchService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class SearchForBooksByAuthor implements Command {

	private static final String PARAM_DELIMETER_AUTHOR = "author";
	private static final String BOOKS_REQUEST_AUTHOR = "Книги по запросу (автор):";
	private static final String BOOKS_REQUEST_AUTHOR_EMPTY = "Книг по запросу (автор) не найдено.";
	private static final String DELIMETER = " - ";
	private static final String AUTHOR_NULL = "Author cann't be null";

	@Override
	public String execute(String request) {

		String author = null;
		String response = "";

		// плюс цифра - количество букв в слове (после которого "вырезается" часть
		// строки) и знак равно

		author = request.substring(request.indexOf(PARAM_DELIMETER_AUTHOR) + 7);

		if (author == null || author.isEmpty()) {
			return AUTHOR_NULL;
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SearchService searchService = serviceProvider.getSearchService();

		try {

			List<Book> books = searchService.searchForBooksByAuthor(author);

			if (books.isEmpty()) {

				response = BOOKS_REQUEST_AUTHOR_EMPTY;

			} else {

				System.out.println("\t" + BOOKS_REQUEST_AUTHOR);
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
