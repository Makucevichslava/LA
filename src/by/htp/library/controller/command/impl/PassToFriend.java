package by.htp.library.controller.command.impl;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class PassToFriend implements Command {

	private static final String PARAM_DELIMETER_TITLE = "title";
	private static final String PARAM_DELIMETER_FRIEND = "friend";

	@Override
	public String execute(String request) {

		String response = null;
		String title = null;
		String friend = null;

		// плюс цифра - количество букв в слове (после которого "вырезается" часть
		// строки) и знак равно

		title = request.substring(request.indexOf(PARAM_DELIMETER_TITLE) + 6,
				request.indexOf(PARAM_DELIMETER_FRIEND) - 1);
		friend = request.substring(request.indexOf(PARAM_DELIMETER_FRIEND) + 7);

		if (title == null || title.isEmpty()) {
			return "Title cann't be null";
		}

		if (friend == null || friend.isEmpty()) {
			return "Friend cann't be null";
		}

		Book bookForFriend = new Book(title);
		
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		LibraryService libraryService = serviceProvider.getLibraryService();

		try {
			response = libraryService.passToFriend(bookForFriend, friend);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return response;
	}

}
