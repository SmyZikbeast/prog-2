package Commands;

import BaseFiles.Movie;
import BaseFiles.Person;
import Manager.CollectionManager;
import Response.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * parent class for all commands
 *
 *
 *
 */
public abstract class Command {
    CollectionManager cm;
    String arg = null;
    Movie movie = null;
    Person person = null;

    Command(CollectionManager cm){
        this.cm = cm;
    }

    public Response execute() throws IOException, SQLException {return null;};

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setMovie(Movie commandMovie) {
        this.movie = commandMovie;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public void execute(String id) throws IOException {};
}
