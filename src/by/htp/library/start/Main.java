package by.htp.library.start;

import by.htp.library.controller.Controller;

public class Main {

	public static void main(String[] args) {

		String role = null;

		Controller controller = new Controller();
		role = controller.executeTask("sign_in login=makarevich-e-v password=111");
		System.out.println("Welcome " + role + "!");

		String passToFriend = controller.executeTask("Pass_To_Friend title=Master and Margarita friend=Ivanov Ivan");
		System.out.println(passToFriend);

		String returnBook = controller.executeTask("Return_Book title=Master and Margarita");
		System.out.println(returnBook);

		String addBook = controller.executeTask(
				"Add_New_Book author=Daniel Keyes title=Flowers for Algernon genre=novel yearOfPublishing=2005 type=paper accessLevel=general access availability=home");
		System.out.println(addBook);

		String deleteBook = controller.executeTask("Delete_Book id=24");
		System.out.println(deleteBook);

		String searchByAuthor = controller.executeTask("Search_For_Books_By_Author author=Armand Louis Caulaincourt");
		System.out.println(searchByAuthor);

		String searchByGenre = controller.executeTask("Search_For_Books_By_Genre genre=education");
		System.out.println(searchByGenre);

		String searchByTitle = controller.executeTask("Search_For_Books_By_Title title=Master and Margarita");
		System.out.println(searchByTitle);

	}

}
