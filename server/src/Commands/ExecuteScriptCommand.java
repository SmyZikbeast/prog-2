package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.Runner;

import java.io.*;

public class ExecuteScriptCommand extends Command{
    private final static int MAX_DEPTH = 5;
    private static int currentDepth = 0;
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String path) throws IOException {
        if (currentDepth > MAX_DEPTH){
            return;
        }
        currentDepth++;
        InputStream inputStream = new FileInputStream(path);
        Runner.start(inputStream, cm);
        CommandManager.addCommand("ExecuteScript");
        currentDepth--;
        System.out.println("success");
    }
}
