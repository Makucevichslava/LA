package by.htp.library.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;

public class FileUserDAO implements UserDAO {

	private static final String SOURSE_PATH_USER = "resources/Users.txt";
	private static final String UNDEFINED_USER = "undefined";

	@Override
	public String signIn(String login, String password) throws DAOException {

		File userDataSourse = new File(SOURSE_PATH_USER);
		BufferedReader reader = null;

		String role = UNDEFINED_USER;

		try {
			reader = new BufferedReader(new FileReader(userDataSourse));
			String userData = null;

			while ((userData = reader.readLine()) != null) {

				if (findAuthMatchesUser(userData, login, password)) {
					role = takeRole(userData);
					return role;
				}
			}

		} catch (IOException e) {
			throw new DAOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

		return role;

	}

	public boolean findAuthMatchesUser(String userData, String login, String password) {

		String[] arrayUserData;
		String tempLogin;
		String tempPassword;

		arrayUserData = userData.split("/");
		tempLogin = arrayUserData[1];
		tempPassword = arrayUserData[2];

		if (login.equals(tempLogin) && password.equals(tempPassword)) {
			return true;
		} else {
			return false;
		}

	}

	public String takeRole(String userData) {

		String[] arrayUserData;
		String role;

		arrayUserData = userData.split("/");
		role = arrayUserData[3];

		return role;
	}

	public boolean findAuthMatchesBook(String bookData, String title) {

		String[] arrayUserData;
		String tempTitle;

		arrayUserData = bookData.split("/");
		tempTitle = arrayUserData[2];

		if (title.equals(tempTitle)) {
			return true;
		} else {
			return false;
		}

	}

}
