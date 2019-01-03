package restserver.restservices;

import com.google.gson.Gson;
import models.Book;
import restserver.handlers.IBookHandler;
import restserver.response.AddBookJson;
import restserver.response.EditBookJson;
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

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveBook(String data) {
        System.out.println(data);
        AddBookJson addBook = gson.fromJson(data, AddBookJson.class);
        Book book = new Book(addBook.get_name(), addBook.get_author(), addBook.get_genres());
        Reply reply = handler.saveBook(book);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/update")
    @Consumes("application/json")
    public Response updateBook(String data) {
        System.out.println(data);
        EditBookJson editBook = gson.fromJson(data, EditBookJson.class);
        Book book = new Book(editBook.get_id(),editBook.get_name(), editBook.get_author(), editBook.get_genres());
        Reply reply = handler.saveBook(book);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/delete/{id}")
    public Response getDeleteBook(@PathParam("id") int bookId) {
        Reply reply = handler.deleteBook(bookId);
        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}