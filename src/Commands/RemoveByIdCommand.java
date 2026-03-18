package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String id) {
    cm.removeId(Integer.valueOf(id));
        CommandManager.addCommand("removeID");
    }
}
