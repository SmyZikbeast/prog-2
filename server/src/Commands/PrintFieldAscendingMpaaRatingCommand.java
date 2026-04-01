package Commands;

import BaseFiles.MpaaRating;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.util.Collections;
import java.util.List;

/**
 * used to get all mpaa rating in ascending order
 *
 *
 *
 */
public class PrintFieldAscendingMpaaRatingCommand extends Command{
    public PrintFieldAscendingMpaaRatingCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        List<MpaaRating> ratings = cm.getCollection().stream().map(s -> s.getMpaaRating()).sorted().toList();
        CommandManager.addCommand("PrintFieldAscendingMpaaRating");
        return new Response("ObjectList", ratings);
    }
}
