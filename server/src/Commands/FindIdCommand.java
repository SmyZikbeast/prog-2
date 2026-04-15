package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Response.Response;

import java.util.Objects;

/**
 * helping class used for update id command
 *
 *
 *
 */
public class FindIdCommand extends Command{
    public FindIdCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public Response execute(){
        if (cm.getCollection().stream().anyMatch(s -> (s.getId() == Integer.parseInt(this.arg) & Objects.equals(s.getUser(), this.user)))){
            return new Response("String", "Found such ID");
        }
        else {
            return new Response("String", "No such ID or no permission");
        }
    }
}
