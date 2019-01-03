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
            List<Book> booksList = repo.findAll();
            List<Book> books = new ArrayList<>();
            Book b1 = new Book();
            for (Book b:booksList) {
                if (b1.getId() != b.getId()) {
                    b1 = b;
                    books.add(b);
                }
            }
            List<BookJson> bookResponse = new ArrayList<>();
            for (Book book : books) {
                bookResponse.add(new BookJson(book.getId(), book.getName(), book.getAuthor(), book.getGenres()));
            }
            String json = gson.toJson(bookResponse);
            System.out.println(json);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }


    public Reply getBook(int id) {
        ArrayList<Book> books = new ArrayList<>(repo.findAll(BookSpecification.getById(id)));
        if (books != null) {
            String json = gson.toJson(books.get(0));
            return new Reply(Status.OK, json);
        }
        ErrorJson errorJson = new ErrorJson("models.Book doesn't exist!");
        return new Reply(Status.NOTFOUND, gson.toJson(errorJson));
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
