package dbal.repository;

import models.Book;

public class BookRepository extends AbstractRepository<Book, Integer> {

    public Class<Book> getDomainClass() {
        return Book.class;
    }
}
