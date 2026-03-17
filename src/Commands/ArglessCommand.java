package Commands;

import Manager.CollectionManager;

public abstract class ArglessCommand extends Command {
    ArglessCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public void execute(String arg){
        this.execute();
    }
}
