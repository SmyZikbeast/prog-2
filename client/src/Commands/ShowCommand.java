package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        for (Movie movie : cm.getCollection()){
            System.out.println(movie.toString()+"\n");

        }
        CommandManager.addCommand("Show");
        return null;
    }
}
