package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        CommandManager.addCommand("ExecuteScript");
    }
}
