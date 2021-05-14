package by.htp.library.service.impl;

import by.htp.library.dao.DAOProvider;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.ClientService;
import by.htp.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {

	public static DAOProvider daoProvider = DAOProvider.getInstance();
	public static UserDAO userDAO = daoProvider.getFileUserImpl();

	private static final String patternLogin = "(^[a-z_])([^(A-Z)(\\$\\&\\?\\#\\%\\^\\/\\) ]){7,20}";
	private static final String UNDEFINED_USER = "undefined";

	@Override
	public String signIn(String login, String password) throws ServiceException {

		String role = UNDEFINED_USER;

		// валидация для логина:
		// - начинается с латинской буквы либо нижнего подчеркивания
		// - допускаются числа
		// - не используются буквы верхнего регистра
		// - не допускаются пробелы, символы $&?#%^/
		// - от 7 до 20 символов

		if (!login.matches(patternLogin)) {
			return role;
		}

		// валидация для пароля:
		// - три числа от 0 до 9

		final String patternPassword = "[0-9]{3}";

		if (!password.matches(patternPassword)) {
			return role;
		}

		try {

			role = userDAO.signIn(login, password);

		} catch (DAOException e) {

			throw new ServiceException(e);
		}

		return role;

	}

}
