package by.htp.library.bean;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idBook;
	private String author;
	private String title;
	private String genre;
	private String yearOfPublishing;
	private String type;
	private String accessLevel;
	private String availability;

	public Book() {

	}

	public Book(int idBook, String author, String title, String genre, String yearOfPublishing, String type,
			String accessLevel, String availability) {
		super();
		this.idBook = idBook;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.yearOfPublishing = yearOfPublishing;
		this.type = type;
		this.accessLevel = accessLevel;
		this.availability = availability;
	}

	public Book(String author, String title, String genre, String yearOfPublishing, String type, String accessLevel,
			String availability) {
		super();
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.yearOfPublishing = yearOfPublishing;
		this.type = type;
		this.accessLevel = accessLevel;
		this.availability = availability;
	}

	public Book(int idBook) {
		this.idBook = idBook;
	}

	public Book(String title) {
		this.title = title;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(String yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessLevel == null) ? 0 : accessLevel.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((availability == null) ? 0 : availability.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + idBook;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((yearOfPublishing == null) ? 0 : yearOfPublishing.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (accessLevel == null) {
			if (other.accessLevel != null)
				return false;
		} else if (!accessLevel.equals(other.accessLevel))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (availability == null) {
			if (other.availability != null)
				return false;
		} else if (!availability.equals(other.availability))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (idBook != other.idBook)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (yearOfPublishing == null) {
			if (other.yearOfPublishing != null)
				return false;
		} else if (!yearOfPublishing.equals(other.yearOfPublishing))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [idBook=" + idBook + ", author=" + author + ", title=" + title + ", genre="
				+ genre + ", yearOfPublishing=" + yearOfPublishing + ", type=" + type + ", accessLevel=" + accessLevel
				+ ", availability=" + availability + "]";
	}

}