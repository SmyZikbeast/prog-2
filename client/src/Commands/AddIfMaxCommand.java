package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class AddIfMaxCommand extends Command{

    public AddIfMaxCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        Movie m = cm.getMovie();
        boolean flag = true;
        for (Movie mv : cm.getCollection()){
            if (m.compareTo(mv) < 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            cm.addMovie(m);
        }
        CommandManager.addCommand("AddIfMax");
        System.out.println("success");
        return null;
    }
}
