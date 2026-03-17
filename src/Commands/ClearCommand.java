package Commands;

import Manager.CollectionManager;

public class ClearCommand extends ArglessCommand{

    public ClearCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        cm.clear();
    }
}
