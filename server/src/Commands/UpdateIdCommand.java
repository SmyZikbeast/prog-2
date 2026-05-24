package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.sql.SQLException;

/**
 * used to replace item with given id
 *
 *
 *
 */
public class UpdateIdCommand extends Command{
    public UpdateIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws SQLException {
        Movie m = (Movie)arg;
        if (!cm.findId(m)){
            m.setUser(user);
            cm.addMovie(m);
            return new Response("String", "Added successfully");
        }
        if (m.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
            cm.setMovie(m);
            return new Response("String", "Updated successfully");
        }
        return new Response("String", "No permission");
    }
}
