package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class ClearCommand extends Command{

    public ClearCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        cm.clear();
        CommandManager.addCommand("Clear");
        return new Response("String", "Successfully cleared");
    }
}
