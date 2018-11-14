package restserver.response;

import java.util.ArrayList;

public class BookJson {
    private int id;
    private String name;
    private String author;
    private ArrayList<String> genres;

    public BookJson(int id, String name, String author, ArrayList<String> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = genres;
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

    public ArrayList<String> getGenres() {
        return genres;
    }
}