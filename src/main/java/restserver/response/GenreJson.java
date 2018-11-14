package restserver.response;

import java.util.ArrayList;

public class GenreJson {
    private int id;
    private String name;

    public GenreJson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}