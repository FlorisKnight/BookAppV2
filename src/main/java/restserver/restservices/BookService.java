package restserver.restservices;

import com.google.gson.Gson;
import models.Book;
import restserver.handlers.IBookHandler;
import restserver.response.Reply;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/book")
public class BookService {
    private static IBookHandler handler;

    public static void setHandler(IBookHandler handler) {
        BookService.handler = handler;
    }

    private Gson gson = new Gson();

    @GET
    @Path("/all")
    public Response getBooks() {
        Reply reply = handler.getBooks();
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") int bookId) {
        Reply reply = handler.getBook(bookId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/genre/{id}")
    public Response getGetBookByGenre(@PathParam("id") int genreId) {
        Reply reply = handler.getBooksByGenre(genreId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveBook(String data) {
        Book book = gson.fromJson(data, Book.class);
        Reply reply = handler.saveBook(book);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}