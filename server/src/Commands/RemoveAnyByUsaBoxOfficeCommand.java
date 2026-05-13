package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * used to remove first element by its usaboxoffice
 *
 *
 *
 */
public class RemoveAnyByUsaBoxOfficeCommand extends Command{
    public RemoveAnyByUsaBoxOfficeCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws SQLException, IOException {

        return new Response("String", "Nothing to remove");
    }
}
