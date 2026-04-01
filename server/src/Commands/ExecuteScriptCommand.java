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
    private final static int MAX_DEPTH = 5;
    private static int currentDepth = 0;
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws FileNotFoundException {
        CommandManager.addCommand("Execute script");
        return new Response("String", "Successfully executed script");
    }
}
