package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.util.LinkedList;
/**
 * used to remove movie by its id
 *
 *
 *
 */
public class RemoveByIdCommand extends Command{
    public RemoveByIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() {
        CommandManager.addCommand("removeID");
        LinkedList<Movie> newList = new LinkedList<>(cm.getCollection().stream().filter(s -> s.getId()!=Integer.valueOf(arg)).toList());
        if (newList.size() != cm.getElementsAmount()){
            cm.setCollection(newList);
            return new Response("String", "Successfully deleted movie "+ arg);
        }
        else {
            return new Response("String", "Nothing to remove");
        }
    }
}
