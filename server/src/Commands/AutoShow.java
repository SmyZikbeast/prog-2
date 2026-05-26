package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

import java.util.stream.Collectors;

/**
 * used to print all collection items
 * sorts them by their distance from (0,0)
 *
 *
 */
public class AutoShow extends Command{
    public AutoShow(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        return new Response("ObjectList",cm.getCollection());
    }
}
