package by.htp.library.dao;

import by.htp.library.dao.exception.DAOException;

public interface UserDAO {

	String signIn(String login, String password) throws DAOException;
	
}
