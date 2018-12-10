package restserver.handlers;

import com.google.gson.Gson;
import dbal.repository.BookRepository;
import dbal.specification.BookSpecification;
import logging.Logger;
import models.Book;
import restserver.response.*;

import java.util.ArrayList;
import java.util.List;


public class BookHandler implements IBookHandler {

    private BookRepository repo;
    private Gson gson;

    public BookHandler(BookRepository repo) {
        this.repo = repo;
        this.gson = new Gson();
    }

    public Reply getBooks() {
        try {
            List<Book> books = repo.findAll();
            List<BookJson> bookResponse = new ArrayList<>();
            for (Book book : books) {
                bookResponse.add(new BookJson(book.getId(), book.getName(), book.getAuthor(), book.getGenreNames()));
            }
            String json = gson.toJson(bookResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }


    public Reply getBook(int id) {
        Book book = repo.findOne(id);
        if (book != null) {
            String json = gson.toJson(book);
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("models.Book doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
    }

    public Reply getBooksByGenre(int id) {
        try {
            List<Book> books = repo.findAll(BookSpecification.getByGenre(id));
            List<BookJson> bookResponse = new ArrayList<>();
            for (Book book : books) {
                bookResponse.add(new BookJson(book.getId(), book.getName(), book.getAuthor(), book.getGenreNames()));
            }
            String json = gson.toJson(bookResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    public Reply saveBook(Book data) {
        Book saved = repo.save(data);
        if (saved.getId() == data.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteBook(int id) {
        try {
            repo.delete(id);
            ErrorJson messageJson = new ErrorJson("Deleted");
            return new Reply(Status.OK, gson.toJson(messageJson));
        } catch (Exception e){
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }
}
