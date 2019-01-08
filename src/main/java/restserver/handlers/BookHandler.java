package restserver.handlers;

import com.google.gson.Gson;
import dbal.repository.BookRepository;
import dbal.repository.GenreRepository;
import dbal.specification.BookSpecification;
import logging.Logger;
import models.Book;
import models.Genre;
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
        try {
            List<Book> booksList = repo.findAll();
            for (Book b:booksList) {
                if (id == b.getId()) {
                    BookJson book = new BookJson(b.getId(),b.getName(),b.getAuthor(),b.getGenres());
                    String json = gson.toJson(book);
                    System.out.println(json);
                    return new Reply(Status.OK, json);
                }
            }

            ErrorJson errorJson = new ErrorJson("Book not found");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getBookByGenre(int id) {
        try {
            List<Book> booksList = repo.findAll();
            List<Book> books = new ArrayList<>();
            Book b1 = new Book();
            for (Book b:booksList) {
                if (b1.getId() != b.getId()) {
                    for(Genre g: b.getGenres()){
                        if(g.getId() == id){
                            b1 = b;
                            books.add(b);
                        }
                    }
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

    @Override
    public Reply getTenBooks(int id) {
        try {
            //filldb();
            List<Book> booksList = repo.findAll();
            List<Book> books = new ArrayList<>();
            Book b1 = new Book();
            for (Book b:booksList) {
                if (b1.getId() != b.getId()) {
                    b1 = b;
                    books.add(b);
                }
            }
            List<Book> booksReply = new ArrayList<>();
            for (int i=id; i < id+10 ;i++){
                if (books.size() > i)
                    booksReply.add(books.get(i));
            }
            List<BookJson> bookResponse = new ArrayList<>();
            for (Book book : booksReply) {
                bookResponse.add(new BookJson(book.getId(), book.getName(), book.getAuthor(), book.getGenres()));
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
        System.out.println(data.getName());
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

    private void filldb(){
        GenreRepository genreRepo = new GenreRepository();
        ArrayList<Genre> genres = new ArrayList<>(genreRepo.findAll());

        for (int i=0; i<1000; i++){
            repo.save(new Book("Name"+(i+99), "Author", genres, true));
        }
    }
}
