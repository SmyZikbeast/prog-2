package Commands;

import Manager.CollectionManager;

import java.io.IOException;

public abstract class Command {
    CollectionManager cm;
    Command(CollectionManager cm){
        this.cm = cm;
    }
    public void execute(){}
    public void execute(String arg) throws IOException {}
}
