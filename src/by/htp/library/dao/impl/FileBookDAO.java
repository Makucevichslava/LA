package by.htp.library.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;

public class FileBookDAO implements BookDAO {

	private static final String SOURSE_PATH_BOOK = "resources/Books.txt";

	private static final String ADD_ERROR = "Error while adding new book to the file.";
	private static final String DELETE_ERROR = "Error while deleting book from file.";
	private static final String FILE_CONNECTION_ERROR = "File connection error, please try later.";

	private static final String LOCATION_HOME = "home";
	private static final String ACCESS_NO_BABY = "no baby";
	private static final String TYPE_PAPER = "paper";
	private static final String FILE_DELIMETER = "/";

	@Override
	public void addBook(Book book) throws DAOException {

		// получить ID (+1 к последней строке)
		// создать строку
		// добавить строку в файл

		int newId;
		String newBookLine;

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File file = new File(path);
		FileWriter fr = null;

		newId = getIdForNewBook();
		newBookLine = createLineForNewBook(book, newId);

		try {
			fr = new FileWriter(file, true);
			fr.write(newBookLine);

		} catch (IOException e) {
			System.out.println(ADD_ERROR);
			throw new DAOException(e);
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private int getIdForNewBook() {

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File booksDataSourse = new File(path);
		BufferedReader reader = null;

		String lastBook = "";
		int newId;

		try {

			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				lastBook = bookData;

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		newId = getNumberOfTheLine(lastBook) + 1;// id для новой книги

		return newId;

	}

	private String createLineForNewBook(Book book, int id) {

		String newBookLine;

		newBookLine = id + FILE_DELIMETER + book.getAuthor() + FILE_DELIMETER + book.getTitle() + FILE_DELIMETER
				+ book.getGenre() + FILE_DELIMETER + book.getYearOfPublishing() + FILE_DELIMETER + book.getType()
				+ FILE_DELIMETER + book.getAccessLevel() + FILE_DELIMETER + book.getAvailability();

		return newBookLine;
	}

	@Override
	public void deleteBook(Book book) throws DAOException {

		int idBook;
		String newContent;

		idBook = book.getIdBook();

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File file = new File(path);

		try {

			newContent = getContentWithoutABook(idBook);
			writeToFile(file, newContent);

		} catch (IOException e) {
			System.out.println(DELETE_ERROR);
			throw new DAOException(e);
		}
	}

	private String getContentWithoutABook(int idBook) throws IOException {

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File booksDataSourse = new File(path);
		BufferedReader reader = null;

		StringBuffer bufferContent = new StringBuffer();

		try {

			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {
				int tempIdBook = getNumberOfTheLine(bookData);
				if (tempIdBook == idBook) {
					continue;
				} else {
					bufferContent.append(bookData).append("\n");
				}

			}

		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}

		String content = bufferContent.toString();
		return content;

	}

	private void writeToFile(File file, String newContent) throws IOException {

		FileOutputStream fileOut = null;

		try {

			fileOut = new FileOutputStream(file);
			fileOut.write(newContent.getBytes());

		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}

	}

	@Override
	public boolean passToFriend(Book book, String friend) throws DAOException {

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File booksDataSourse = new File(path);
		BufferedReader reader = null;
		String title = book.getTitle();

		try {

			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				// книга должна существовать, быть бумажной, находиться дома

				if (findSuitableBookForFriend(bookData, title)) {

					// книга должна быть доступна для детей

					if (getAccessForBaby(bookData)) {
						return false;
					} else {

						// создать строку замены
						// получить номер строки замены по id
						// создать новый файл
						// записать новый файл

						String bookGivenToFriend = changeLine(bookData, friend);

						int lineNumberToReplace = getNumberOfTheLine(bookGivenToFriend);

						String newContent = getContentWithNewLocation(bookGivenToFriend, lineNumberToReplace);

						writeToFile(booksDataSourse, newContent);

						return true;

					}

				}
			}

		} catch (IOException e) {
			System.out.println(FILE_CONNECTION_ERROR);
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

		return false;
	}

	private boolean findSuitableBookForFriend(String bookData, String title) {

		String[] arrayBookData;
		String tempTitle;
		String tempType;
		String tempLocation;

		arrayBookData = bookData.split(FILE_DELIMETER);

		tempTitle = arrayBookData[2];
		tempType = arrayBookData[5];
		tempLocation = arrayBookData[arrayBookData.length - 1];

		if (title.equals(tempTitle) && tempType.equals(TYPE_PAPER) && tempLocation.equals(LOCATION_HOME)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean findSuitableBookToReturn(String bookData, String title) {

		String[] arrayBookData;
		String tempTitle;
		String tempLocation;

		arrayBookData = bookData.split(FILE_DELIMETER);

		tempTitle = arrayBookData[2];
		tempLocation = arrayBookData[arrayBookData.length - 1];

		if (title.equals(tempTitle) && !(tempLocation.equals(LOCATION_HOME))) {
			return true;
		} else {
			return false;
		}

	}

	private boolean getAccessForBaby(String bookData) {

		String[] arrayBookData;

		String tempAccess;

		arrayBookData = bookData.split(FILE_DELIMETER);
		tempAccess = arrayBookData[6];

		if (tempAccess.equals(ACCESS_NO_BABY)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean returnBook(String title) throws DAOException {

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File booksDataSourse = new File(path);
		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			while ((bookData = reader.readLine()) != null) {

				// книга должна существовать, находиться вне дома

				if (findSuitableBookToReturn(bookData, title)) {

					String returnedBook = changeLine(bookData, LOCATION_HOME);

					int lineNumberToReplace = getNumberOfTheLine(returnedBook);

					String newContent = getContentWithNewLocation(returnedBook, lineNumberToReplace);

					writeToFile(booksDataSourse, newContent);

					return true;
				}

			}

		} catch (IOException e) {
			System.out.println(FILE_CONNECTION_ERROR);
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new DAOException(e);
				}
			}
		}

		return false;
	}

	private String changeLine(String bookData, String newLocation) {

		String[] arrayBookData;
		arrayBookData = bookData.split(FILE_DELIMETER);

		StringBuffer tempBuffer = new StringBuffer();

		for (int i = 0; i < arrayBookData.length - 1; i++) {
			tempBuffer.append(arrayBookData[i]);
			tempBuffer.append(FILE_DELIMETER);
		}

		tempBuffer.append(newLocation);

		String resultLine = tempBuffer.toString();

		return resultLine;

	}

	private int getNumberOfTheLine(String bookLine) {

		String numberOfLine;
		int arithmeticNumberOfLine;

		numberOfLine = bookLine.substring(0, bookLine.indexOf(FILE_DELIMETER));
		arithmeticNumberOfLine = Integer.parseInt(numberOfLine);

		return arithmeticNumberOfLine;

	}

	private String getContentWithNewLocation(String changingLine, int lineNumberToReplace) throws IOException {

		String path = new File(SOURSE_PATH_BOOK).getAbsolutePath();
		File booksDataSourse = new File(path);
		BufferedReader reader = null;

		StringBuffer bufferContent = new StringBuffer();

		try {

			reader = new BufferedReader(new FileReader(booksDataSourse));
			String bookData = null;

			int iter = 1; // итератор для номера строки

			while ((bookData = reader.readLine()) != null) {

				if (iter == lineNumberToReplace) {
					bufferContent.append(changingLine).append("\n");
				} else {
					bufferContent.append(bookData).append("\n");
				}

				iter++;

			}

		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}

		String content = bufferContent.toString();
		return content;

	}

}
