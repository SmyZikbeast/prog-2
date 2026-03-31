import BaseFiles.Movie;
import Commands.Command;

public class ServerPackage {
    Command command;
    String arg = null;
    Movie movie = null;
    public ServerPackage(Command c, String arg, Movie movie){
        this.command = c;
        this.arg = arg;
        this.movie = movie;
    }
    public ServerPackage(Command c, String arg){
        this.command = c;
        this.arg = arg;
    }
    public ServerPackage(Command c, Movie m){
        this.command = c;
        this.movie = m;
    }
}
