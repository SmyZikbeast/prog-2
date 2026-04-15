package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.io.IOException;
import java.sql.SQLException;

public class AddIfMaxCommand extends Command{

    public AddIfMaxCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * adds new movie to collection if it's usaBoxOffice if max
     *
     *
     *
     */
    @Override
    public Response execute() throws SQLException, IOException {
        Movie m = this.movie;
        CommandManager.addCommand("AddIfMax");
        if(cm.getCollection().stream().allMatch(s -> m.compareTo(s)>0)){
            cm.addMovie(m);
            cm.load();
            return new Response("String", "Successfully added");
        }
        else {
            return new Response("String", "Movie is not max, nothing added");
        }
    }
}
