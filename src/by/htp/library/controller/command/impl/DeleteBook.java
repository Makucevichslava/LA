package by.htp.library.controller.command.impl;

import by.htp.library.controller.Command;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class DeleteBook implements Command {

	@Override
	public String execute(String request) {

		String response = null;
		String id = null;

		id = request.substring(request.indexOf("id") + 3);

		if (id == null || id.isEmpty()) {
			return "Id cann't be null";
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		LibraryService libraryService = serviceProvider.getLibraryService();

		try {
			response = libraryService.deleteBook(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return response;
	}

}
