package Response;

import BaseFiles.Movie;
import BaseFiles.Person;
import com.google.gson.annotations.Expose;
/**
 * class used to send objects from client to server
 *
 *
 *
 */
public class Request {
    @Expose
    String type;
    @Expose
    String arg;
    @Expose
    Movie movie;

    public String getArg() {
        return arg;
    }

    public Person getPerson() {
        return person;
    }

    @Expose
    Person person;

    public Request(String commandType, String token, Movie movie, Person person) {
        this.type = commandType;
        this.arg = token;
        this.movie = movie;
        this.person = person;
    }

    public String getType() {
        return this.type;
    }

    public Movie getMovie() {
        return this.movie;
    }
}
