package Commands;

import Manager.CollectionManager;
import Response.Response;
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
        if (cm.getCollection().stream().anyMatch(s -> s.getId() == Integer.valueOf(this.arg))){
            return new Response("String", "Found such ID");
        }
        else {
            return new Response("String", "No such ID");
        }
    }
}
