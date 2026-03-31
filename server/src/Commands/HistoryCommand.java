package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class HistoryCommand extends Command{
    public HistoryCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.addCommand("history");
        return new Response("StringList", CommandManager.getHistory());
    }

}
