package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;

public class AddCommand extends ArglessCommand{
    public AddCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        Movie m = cm.getMovie();
        cm.addMovie(m);
    }
}
