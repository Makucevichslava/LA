package by.htp.library.service;

import by.htp.library.service.impl.ClientServiceImpl;
import by.htp.library.service.impl.LibraryServiceImpl;
import by.htp.library.service.impl.SearchServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private final ClientService clientService = new ClientServiceImpl();
	private final LibraryService libraryService = new LibraryServiceImpl();
	private final SearchService searchService = new SearchServiceImpl();

	private ServiceProvider() {

	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	public SearchService getSearchService() {
		return searchService;
	}

}
