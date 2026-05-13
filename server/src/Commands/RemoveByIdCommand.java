package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * used to remove movie by its id
 *
 *
 *
 */
public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws SQLException, IOException {

        return new Response("String", "Nothing to remove");
    }
}
