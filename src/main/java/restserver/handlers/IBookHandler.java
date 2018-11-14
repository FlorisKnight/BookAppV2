package restserver.handlers;

import models.Book;
import restserver.response.Reply;

public interface IBookHandler {

    Reply getBooks();

    Reply getBook(int id);

    Reply getBooksByGenre(int id);

    Reply saveBook(Book data);
}
