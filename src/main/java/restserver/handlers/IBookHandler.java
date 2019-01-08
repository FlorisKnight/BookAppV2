package restserver.handlers;

import models.Book;
import restserver.response.AddBookJson;
import restserver.response.Reply;

public interface IBookHandler {

    Reply getBooks();

    Reply getBook(int id);

    Reply getBookByGenre(int id);

    Reply getTenBooks(int id);

    Reply saveBook(Book data);

    Reply deleteBook(int id);
}
