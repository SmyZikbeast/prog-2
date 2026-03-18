package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

public class ClearCommand extends Command{

    public ClearCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        cm.clear();
        CommandManager.addCommand("Clear");
    }
}
