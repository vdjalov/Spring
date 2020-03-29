package app.repository;

import java.util.List;

import app.models.Book;

public class BookRepository {

	
	
	public List<Book> getAllBooks() {
			List<Book> books = List.of(
							new Book(1, "Pod Igoto"),
							new Book(2, "Pod Igoto 1"),
							new Book(3, "Pod Igoto 2"),
							new Book(4, "Pod Igoto 3"),
							new Book(5, "Pod Igoto 4"),
							new Book(6, "Pod Igoto 5"),
							new Book(7, "Pod Igoto 6"),
							new Book(8, "Pod Igoto 7"),
							new Book(9, "Pod Igoto 8")
					);
		return books;
	}
	
}
