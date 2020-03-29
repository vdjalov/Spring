package app.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import app.models.Book;

public interface BookService {

	Page<Book>findPaginated(Pageable pageable);

	
	
}
