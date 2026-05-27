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
        cm.addMovie((Movie) this.arg);
        return new Response("String", "Success");
    }
}
