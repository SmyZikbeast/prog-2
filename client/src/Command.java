import BaseFiles.Movie;
import com.google.gson.annotations.Expose;

public class Command {
    @Expose
    String type;
    @Expose
    String arg;
    @Expose
    Movie movie;

    public Command(String commandType, String token, Movie movie) {
        this.type = commandType;
        this.arg = token;
        this.movie = movie;
    }
}
