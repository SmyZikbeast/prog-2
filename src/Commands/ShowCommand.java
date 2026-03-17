package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        for (Movie movie : cm.getCollection()){
            System.out.println(movie.toString()+"\n");
        }
    }
}
