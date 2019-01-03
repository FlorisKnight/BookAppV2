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


public class GenreHandler implements IGenreHandler {

    private GenreRepository repo;
    private Gson gson;

    public GenreHandler(GenreRepository repo) {
        this.repo = repo;
        this.gson = new Gson();
    }

    public Reply getGenres() {
        try {
            List<Genre> genres = repo.findAll();
            List<GenreJson> genreResponse = new ArrayList<>();
            for (Genre g : genres) {
                genreResponse.add(new GenreJson(g.getId(), g.getName()));
            }
            String json = gson.toJson(genreResponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    public Reply saveGenre(Genre data) {
        Genre saved = repo.save(data);
        if (saved.getId() == data.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }
        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteGenre(int id) {
        try {
            repo.delete(id);
            ErrorJson messageJson = new ErrorJson("Deleted");
            return new Reply(Status.OK, gson.toJson(messageJson));
        } catch (Exception e){
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    public Reply getBooksByGenre(int id) {
        try {
            Genre genre = repo.findOne(id);
            List<BookJson> books = new ArrayList<>();
            for (Book book : genre.getBooks()) {
                books.add((new BookJson(book.getId(), book.getName(), book.getAuthor(), book.getGenres())));
            }
            String json = gson.toJson(books);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");
            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }
}
