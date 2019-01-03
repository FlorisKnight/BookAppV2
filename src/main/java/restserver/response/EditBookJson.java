package restserver.response;

import java.util.ArrayList;

public class EditBookJson {
    private int _id;
    private String _name;
    private String _author;
    private ArrayList<GenreJson> _genres;

    public EditBookJson(int id, String name, String author, ArrayList<GenreJson> genres) {
        this._id = id;
        this._name = name;
        this._author = author;
        this._genres = genres;
    }

    public int get_id() {
        return _id;
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
