package Commands;

import BaseFiles.Movie;
import BaseFiles.Person;
import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.ElementInputManager;
import Response.Response;

public class CountLessThanScreenwriterCommand extends Command{
    public CountLessThanScreenwriterCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        long amount = cm.getCollection().stream().filter(m -> m.getScreenwriter().compareTo(this.person)<0).count();
        return new Response("String", String.valueOf(amount));
    }
}
