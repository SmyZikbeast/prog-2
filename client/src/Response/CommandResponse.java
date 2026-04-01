package Response;

import BaseFiles.Movie;
import BaseFiles.Person;
import com.google.gson.annotations.Expose;
/**
 * class used to send objects from server
 *
 *
 *
 */
public class CommandResponse {
    @Expose
    String type;
    @Expose
    String arg;
    @Expose
    Movie movie;
    @Expose
    Person person;

    public CommandResponse(String commandType, String token, Movie movie, Person person) {
        this.type = commandType;
        this.arg = token;
        this.movie = movie;
        this.person = person;
    }

    public String getType() {
        return this.type;
    }
}
