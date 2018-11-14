package dbal.repository;

import models.Genre;

public class GenreRepository extends AbstractRepository<Genre, Integer> {
    @Override
    public Class<Genre> getDomainClass() {
        return Genre.class;
    }
}
