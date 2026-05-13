package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.io.*;
/**
 * used to execute scripts on client
 *
 *
 *
 */
public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws FileNotFoundException {

        return new Response("String", "Successfully executed script");
    }
}
