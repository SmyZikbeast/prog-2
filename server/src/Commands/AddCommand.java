package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class AddCommand extends Command{
    public AddCommand(CollectionManager cm) {
        super(cm);
    }
    /**
     * adds new movie to collection
     *
     *
     *
     */
    @Override
    public Response execute(){
        Movie m = this.movie;
        cm.addMovie(m);
        CommandManager.addCommand("Add");
        return new Response("String", "Success");
    }
}
