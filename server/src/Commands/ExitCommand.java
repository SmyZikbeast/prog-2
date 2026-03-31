package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class ExitCommand extends Command{
    public ExitCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.clear();
        return new Response("String", "Closing client...");
    }
}
