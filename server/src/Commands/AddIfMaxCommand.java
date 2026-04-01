package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class AddIfMaxCommand extends Command{

    public AddIfMaxCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * adds new movie to colection if it's usaBoxOffice if max
     *
     *
     *
     */
    @Override
    public Response execute(){
        Movie m = this.movie;
        CommandManager.addCommand("AddIfMax");
        if(cm.getCollection().stream().allMatch(s -> m.compareTo(s)>0)){
            cm.addMovie(m);
            return new Response("String", "Successfully added");
        }
        else {
            return new Response("String", "Movie is not max, not added");
        }
    }
}
