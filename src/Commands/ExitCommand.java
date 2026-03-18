package Commands;

import Manager.CollectionManager;

public class ExitCommand extends Command{
    public ExitCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        System.exit(0);
    }
}
