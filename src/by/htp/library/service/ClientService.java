package by.htp.library.service;

import by.htp.library.service.exception.ServiceException;

public interface ClientService {

	String signIn(String login, String password) throws ServiceException;

}
