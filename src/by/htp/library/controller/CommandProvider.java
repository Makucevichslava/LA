package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.controller.command.impl.AddNewBook;
import by.htp.library.controller.command.impl.DeleteBook;
import by.htp.library.controller.command.impl.PassToFriend;
import by.htp.library.controller.command.impl.ReturnBook;
import by.htp.library.controller.command.impl.SearchForBooksByAuthor;
import by.htp.library.controller.command.impl.SearchForBooksByGenre;
import by.htp.library.controller.command.impl.SearchForBooksByTitle;
import by.htp.library.controller.command.impl.SingIn;
import by.htp.library.controller.command.impl.WrongRequest;

final class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.SIGN_IN, new SingIn());
		repository.put(CommandName.ADD_NEW_BOOK, new AddNewBook());
		repository.put(CommandName.DELETE_BOOK, new DeleteBook());
		repository.put(CommandName.PASS_TO_FRIEND, new PassToFriend());
		repository.put(CommandName.RETURN_BOOK, new ReturnBook());
		repository.put(CommandName.SEARCH_FOR_BOOKS_BY_AUTHOR, new SearchForBooksByAuthor());
		repository.put(CommandName.SEARCH_FOR_BOOKS_BY_GENRE, new SearchForBooksByGenre());
		repository.put(CommandName.SEARCH_FOR_BOOKS_BY_TITLE, new SearchForBooksByTitle());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());

	}

	Command getCommand(String name) {

		CommandName commandName = null;
		Command command = null;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}

		return command;
	}

}
