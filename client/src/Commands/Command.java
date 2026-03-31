package Commands;

import Manager.CollectionManager;
import Response.Response;

import java.io.IOException;

public abstract class Command {
    CollectionManager cm;
    Command(CollectionManager cm){
        this.cm = cm;
    }
    public Response execute(){
        return null;
    }
    public void execute(String arg) throws IOException {}
}
