package models;

import restserver.response.GenreJson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            CascadeType.MERGE,
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Book(String name, String author, ArrayList<GenreJson> genres) {
        this.name = name;
        this.author = author;
        this.genres = new HashSet<Genre>();
        if (genres != null) {
            for (GenreJson g : genres) {
                this.genres.add(new Genre(g.getId(), g.getName()));
            }
        }
    }

    public Book(int id, String name, String author, ArrayList<GenreJson> genres) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genres = new HashSet<Genre>();
        if (genres != null) {
            for (GenreJson g : genres) {
                this.genres.add(new Genre(g.getId(), g.getName()));
            }
        }
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

    public ArrayList<Genre> getGenres() {
        return new ArrayList<>(genres);
    }
}
