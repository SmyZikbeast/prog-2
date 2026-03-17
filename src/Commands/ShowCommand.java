package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;

public class ShowCommand extends ArglessCommand{
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        for (Movie movie : cm.getCollection()){
            System.out.println(movie.toString());
        }
    }
}
