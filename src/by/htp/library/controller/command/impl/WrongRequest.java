package by.htp.library.controller.command.impl;

import by.htp.library.controller.Command;

public class WrongRequest implements Command {

	private static final String RESPONSE = "Wrong request";

	@Override
	public String execute(String request) {

		return RESPONSE;

	}

}
