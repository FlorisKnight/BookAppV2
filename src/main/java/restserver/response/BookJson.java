package restserver.response;

import models.Genre;

import java.util.ArrayList;

public class BookJson {
    private int id;
    private String name;
    private String author;
    private ArrayList<GenreJson> genres;

    public BookJson(int id, String name, String author, ArrayList<Genre> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = new ArrayList<>();
        if (genres != null)
            for (Genre g: genres){
            this.genres.add(new GenreJson(g.getId(),g.getName()));
            }

    }

    public BookJson(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public ArrayList<GenreJson> getGenres() {
        return genres;
    }
}