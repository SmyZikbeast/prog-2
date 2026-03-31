package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.Runner;
import Response.Response;

import java.io.*;

public class ExecuteScriptCommand extends Command{
    private final static int MAX_DEPTH = 5;
    private static int currentDepth = 0;
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws FileNotFoundException {
        if (currentDepth > MAX_DEPTH){
            return new Response("String", "Recursion depth exceeded");
        }
        currentDepth++;
        InputStream inputStream = new FileInputStream(this.arg);
        Runner.start(inputStream, cm);
        CommandManager.addCommand("ExecuteScript");
        currentDepth--;
        return new Response("String", "Successfully executed script");
    }
}
