package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.Runner;

import java.io.*;

public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        Runner.start(inputStream, cm);
        CommandManager.addCommand("ExecuteScript");
    }
}
