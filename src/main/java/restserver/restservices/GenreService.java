package restserver.restservices;

import com.google.gson.Gson;
import models.Genre;
import restserver.handlers.IBookHandler;
import restserver.handlers.IGenreHandler;
import restserver.response.AddBookJson;
import restserver.response.Reply;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/genre")
public class GenreService {
    private static IGenreHandler handler;

    public static void setHandler(IGenreHandler handler) {
        GenreService.handler = handler;
    }

    private Gson gson = new Gson();

    @GET
    @Path("/all")
    public Response getGenres() {
        Reply reply = handler.getGenres();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveBook(String data) {
        System.out.println(data);
        Genre genre = gson.fromJson(data, Genre.class);
        Reply reply = handler.saveGenre(genre);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/delete/{id}")
    public Response getDeleteBook(@PathParam("id") int genreId) {
        Reply reply = handler.deleteGenre(genreId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/books/{id}")
    public Response getGetBookByGenre(@PathParam("id") int genreId) {
        Reply reply = handler.getBooksByGenre(genreId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}