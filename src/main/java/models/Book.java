package models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String author;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Book(int id, String name, String author, ArrayList<Genre> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = genres;
    }

    public Book() {
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

    public List<Genre> getGenres() {
        return genres;
    }

    public ArrayList<String> getGenreNames(){
        ArrayList<String> genreNames = new ArrayList<>();
        for(Genre g: genres) {
            genreNames.add(g.getName());
        }
        return genreNames;
    }
}
