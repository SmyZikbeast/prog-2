package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
/**
 * used to print all collection items
 * sorts them by their distance from (0,0)
 *
 *
 */
public class ShowCommand extends Command{
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.addCommand("Show");
        return new Response("ObjectList",cm.getCollection());
    }
}
