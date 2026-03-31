package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;

public class AddCommand extends Command{
    public AddCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        Movie m = cm.getMovie();
        cm.addMovie(m);
        CommandManager.addCommand("Add");
    }
}
