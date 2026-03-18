package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;

public class AddIfMinCommand extends Command{

    public AddIfMinCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
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
        CommandManager.addCommand("AddIfMin");
    }
}
