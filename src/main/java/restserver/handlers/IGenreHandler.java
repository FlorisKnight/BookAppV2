package restserver.handlers;

import models.Genre;
import restserver.response.AddBookJson;
import restserver.response.Reply;

public interface IGenreHandler {

    Reply getGenres();

    Reply saveGenre(Genre data);

    Reply deleteGenre(int id);

    Reply getBooksByGenre(int id);
}
