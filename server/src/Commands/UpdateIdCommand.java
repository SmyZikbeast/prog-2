package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
/**
 * used to replace item with given id
 *
 *
 *
 */
public class UpdateIdCommand extends Command{
    public UpdateIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        cm.setMovie(Integer.valueOf(arg), this.movie);
        CommandManager.addCommand("UpdateId");
        return new Response("String", "Successfully updated");
    }
}
