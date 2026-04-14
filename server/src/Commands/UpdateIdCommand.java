package Commands;

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
        if(cm.setMovie(Integer.parseInt(arg), movie)){
            CommandManager.addCommand("UpdateId");
            return new Response("String", "Successfully updated");
        }
        return new Response("String", "Nothing to update");
    }
}
