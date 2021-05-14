package by.htp.library.controller.command.impl;

import by.htp.library.controller.Command;
import by.htp.library.service.ClientService;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.exception.ServiceException;

public class SingIn implements Command {

	private static final String PARAM_DELIMETER_LOGIN = "login";
	private static final String PARAM_DELIMETER_PASSWORD = "password";
	private static final char paramDelimeter = ' ';

	@Override
	public String execute(String request) {

		String login = null;
		String password = null;
		String response = null;

		// get parameters from request and initialize the variables login and password

		login = request.substring(request.indexOf(PARAM_DELIMETER_LOGIN) + 6,
				request.indexOf(paramDelimeter, request.indexOf(PARAM_DELIMETER_LOGIN)));
		password = request.substring(request.indexOf(PARAM_DELIMETER_PASSWORD) + 9);

		if (login == null || login.isEmpty()) {
			return "Login cann't be null";
		}

		if (password == null || password.isEmpty()) {
			return "Password cann't be null";
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		ClientService clientService = serviceProvider.getClientService();

		try {
			response = clientService.signIn(login, password);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return response;
	}

}
