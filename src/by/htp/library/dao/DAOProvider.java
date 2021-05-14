package by.htp.library.dao;

import by.htp.library.dao.impl.FileBookDAO;
import by.htp.library.dao.impl.FileSearchDAO;
import by.htp.library.dao.impl.FileUserDAO;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private final BookDAO fileBookImpl = new FileBookDAO();
	private final UserDAO fileUserImpl = new FileUserDAO();
	private final SearchDAO fileSearchDAO = new FileSearchDAO();

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public BookDAO getFileBookImpl() {
		return fileBookImpl;
	}

	public UserDAO getFileUserImpl() {
		return fileUserImpl;
	}

	public SearchDAO getFileSearchImpl() {
		return fileSearchDAO;
	}
}
