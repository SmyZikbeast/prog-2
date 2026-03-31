package Response;

import BaseFiles.Movie;
import BaseFiles.Person;
import com.google.gson.annotations.Expose;

public class CommandResponse {
    @Expose
    String type;
    @Expose
    String arg;
    @Expose
    Movie movie;
    @Expose
    Person person;

    public CommandResponse(String commandType, String arg, Movie movie, Person person) {
        this.type = commandType;
        this.arg = arg;
        this.movie = movie;
        this.person = person;
    }

    public String getType() {
        return this.type;
    }

    public String getArg() {
        return this.arg;
    }

    public Movie getMovie() {
        return this.movie;
    }
    public Person getPerson(){
        return this.person;
    }
}
