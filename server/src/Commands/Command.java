package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Response.Response;

import java.io.IOException;

public abstract class Command {
    CollectionManager cm;
    String arg = null;
    Movie movie;
    Command(CollectionManager cm){
        this.cm = cm;
    }
    public Response execute(){
        return null;
    }
    public void execute(String arg) throws IOException {}

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setMovie(Movie commandMovie) {
        this.movie = commandMovie;
    }
}
