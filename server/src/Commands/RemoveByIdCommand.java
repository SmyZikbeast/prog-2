package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
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
        CommandManager.addCommand("removeID");
        Movie mv = cm.getCollection().stream().filter(s -> Integer.valueOf(arg) == s.getId()).findAny().orElse(null);
        if (mv != null){
            cm.removeId(mv.getId());
            cm.load();
            return new Response("String", "Successfully removed");
        }
        return new Response("String", "Nothing to remove");
    }
}
