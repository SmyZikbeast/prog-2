package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class AddIfMinCommand extends Command{

    public AddIfMinCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        Movie m = this.movie;
        CommandManager.addCommand("AddIfMax");
        if(cm.getCollection().stream().allMatch(s -> m.compareTo(s)<0)){
            cm.addMovie(m);
            return new Response("String", "Successfully added");
        }
        else {
            return new Response("String", "Movie is not min, not added");
        }
    }
}
