package Commands;

import Manager.CollectionManager;

public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String id) {
    cm.removeId(Integer.valueOf(id));
    }
}
