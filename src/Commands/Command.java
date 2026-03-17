package Commands;

import Manager.CollectionManager;

public abstract class Command {
    CollectionManager cm;
    Command(CollectionManager cm){
        this.cm = cm;
    }
    public void execute(){}
}
