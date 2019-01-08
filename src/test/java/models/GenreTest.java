package models;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restserver.response.GenreJson;

import java.util.ArrayList;

public class GenreTest {
    Genre genre = new Genre();
    @Before
    public void  TestInitialize() {
        genre = new Genre(1,"Comic");
    }

    @Test
    public void getId() {
        Assert.assertEquals(genre.getId(),1);
    }

    @Test
    public void setId() {
        genre.setId(2);
        Assert.assertEquals(genre.getId(),2);
    }

    @Test
    public void getName() {
        Assert.assertEquals(genre.getName(),"Comic");
    }

    @Test
    public void setName() {
        genre.setName("renamed");
        Assert.assertEquals(genre.getName(),"renamed");
    }
}
