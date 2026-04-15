package Response;

import BaseFiles.*;
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
    @Expose
    byte[] arg2;
    public String getArg() {
        return arg;
    }
    public byte[] getArg2() {
        return arg2;
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
    public Request(String commandType, String token, Movie movie, Person person, byte[] bytes) {
        this.type = commandType;
        this.arg = token;
        this.movie = movie;
        this.person = person;
        this.arg2 = bytes;
    }

    public String getType() {
        return this.type;
    }

    public Movie getMovie() {
        return this.movie;
    }
}
