package by.htp.library.controller.command.impl;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class AddNewBook implements Command {

	private static final String PARAM_DELIMETER_AUTHOR = "author";
	private static final String PARAM_DELIMETER_TITLE = "title";
	private static final String PARAM_DELIMETER_GENRE = "genre";
	private static final String PARAM_DELIMETER_YEAR_OF_PUBLISHING = "yearOfPublishing";
	private static final String PARAM_DELIMETER_TYPE = "type";
	private static final String PARAM_DELIMETER_ACCESSLEVEL = "accessLevel";
	private static final String PARAM_DELIMETER_AVAILABILITY = "availability";

	@Override
	public String execute(String request) {

		String response = null;
		String author = null;
		String title = null;
		String genre = null;
		String yearOfPublishing = null;
		String type = null;
		String accessLevel = null;
		String availability = null;

		// плюс цифра - количество букв в слове (после которого "вырезается" часть
		// строки) и знак равно

		author = request.substring(request.indexOf(PARAM_DELIMETER_AUTHOR) + 7,
				request.indexOf(PARAM_DELIMETER_TITLE) - 1);
		title = request.substring(request.indexOf(PARAM_DELIMETER_TITLE) + 6,
				request.indexOf(PARAM_DELIMETER_GENRE) - 1);
		genre = request.substring(request.indexOf(PARAM_DELIMETER_GENRE) + 6,
				request.indexOf(PARAM_DELIMETER_YEAR_OF_PUBLISHING) - 1);
		yearOfPublishing = request.substring(request.indexOf(PARAM_DELIMETER_YEAR_OF_PUBLISHING) + 17,
				request.indexOf(PARAM_DELIMETER_TYPE) - 1);
		type = request.substring(request.indexOf(PARAM_DELIMETER_TYPE) + 5,
				request.indexOf(PARAM_DELIMETER_ACCESSLEVEL) - 1);
		accessLevel = request.substring(request.indexOf(PARAM_DELIMETER_ACCESSLEVEL) + 12,
				request.indexOf(PARAM_DELIMETER_AVAILABILITY) - 1);
		availability = request.substring(request.indexOf(PARAM_DELIMETER_AVAILABILITY) + 13);

		if (author == null || author.isEmpty()) {
			return "Author cann't be null";
		}

		if (title == null || title.isEmpty()) {
			return "Title cann't be null";
		}

		if (genre == null || genre.isEmpty()) {
			return "Genre cann't be null";
		}

		if (yearOfPublishing == null || yearOfPublishing.isEmpty()) {
			return "Year of publishing cann't be null";
		}

		if (type == null || type.isEmpty()) {
			return "Type cann't be null";
		}

		if (accessLevel == null || accessLevel.isEmpty()) {
			return "Access level cann't be null";
		}

		if (availability == null || availability.isEmpty()) {
			return "Availability cann't be null";
		}

		Book addedBook = new Book(author, title, genre, yearOfPublishing, type, accessLevel, availability);

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		LibraryService libraryService = serviceProvider.getLibraryService();

		try {
			response = libraryService.addNewBook(addedBook);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return response;
	}

}
