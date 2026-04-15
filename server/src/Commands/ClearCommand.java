package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.sql.SQLException;

public class ClearCommand extends Command{

    public ClearCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * clears collection
     *
     *
     *
     */
    @Override
    public Response execute() throws SQLException {
        cm.clear();
        CommandManager.addCommand("Clear");
        return new Response("String", "Successfully cleared");
    }
}
