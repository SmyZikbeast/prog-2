package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class CountLessThanScreenwriterCommand extends Command{
    public CountLessThanScreenwriterCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * used to count movies that have lower screenwriter than given
     *
     *
     *
     */
    @Override
    public Response execute(){
        return new Response("String", "1");
    }
}
