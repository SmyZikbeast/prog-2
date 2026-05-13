package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
/**
 * used to get last 10 commands
 *
 *
 *
 */
public class HistoryCommand extends Command{
    public HistoryCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){

        return new Response("StringList", CommandManager.getHistory());
    }

}
