package restserver.response;

import models.Genre;

import java.util.ArrayList;

public class AddBookJson {
    private String _name;
    private String _author;
    private ArrayList<GenreJson> _genres;

    public AddBookJson(String name, String author, ArrayList<GenreJson> genres) {
        this._name = name;
        this._author = author;
        this._genres = genres;
    }


    public String get_name() {
        return _name;
    }

    public String get_author() {
        return _author;
    }

    public ArrayList<GenreJson> get_genres() {
        return _genres;
    }
}
