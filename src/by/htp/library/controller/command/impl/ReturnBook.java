package by.htp.library.controller.command.impl;

import by.htp.library.controller.Command;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class ReturnBook implements Command {

	@Override
	public String execute(String request) {

		String response = null;
		String title = null;

		title = request.substring(request.indexOf("title") + 6);

		if (title == null || title.isEmpty()) {
			return "Title cann't be null";
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		LibraryService libraryService = serviceProvider.getLibraryService();

		try {
			response = libraryService.returnBook(title);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return response;
	}

}
