package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class AddCommand extends Command{
    public AddCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        Movie m = cm.getMovie();
        cm.addMovie(m);
        CommandManager.addCommand("Add");
        return null;
    }
}
