package Response;

import BaseFiles.Movie;
import com.google.gson.annotations.Expose;

public class CommandResponse {
    @Expose
    String type;
    @Expose
    String arg;
    @Expose
    Movie movie;

    public CommandResponse(String commandType, String arg, Movie movie) {
        this.type = commandType;
        this.arg = arg;
        this.movie = movie;
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
}
