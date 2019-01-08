package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restserver.response.GenreJson;

import java.util.ArrayList;

public class BookTest {
    Book book;
    @Before
    public void  TestInitialize() {
        ArrayList<GenreJson> genres = new ArrayList<>();
        genres.add(new GenreJson(1,"Comic"));
        genres.add(new GenreJson(2,"Fable"));
        genres.add(new GenreJson(3,"Action"));

        book = new Book(1,"name", "author", genres);
    }


    @Test
    public void getId() {
        Assert.assertEquals(book.getId(),1);
    }

    @Test
    public void getName() {
        Assert.assertEquals(book.getName(),"name");
    }

    @Test
    public void getAuthor() {
        Assert.assertEquals(book.getAuthor(),"author");
    }

    @Test
    public void getGenres() {
        Assert.assertEquals(book.getGenres().size(),3);
    }
}
