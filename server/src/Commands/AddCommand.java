package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.sql.SQLException;

public class AddCommand extends Command{
    public AddCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * adds new movie to collection
     *
     *
     *
     */
    @Override
    public Response execute() throws SQLException {
        Movie m = this.movie;
        cm.addMovie(m);
        cm.load();
        CommandManager.addCommand("Add");
        return new Response("String", "Success");
    }
}
