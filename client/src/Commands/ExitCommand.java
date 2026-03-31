package Commands;

import Manager.CollectionManager;
import Response.Response;

public class ExitCommand extends Command{
    public ExitCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        System.exit(0);
        return null;
    }
}
