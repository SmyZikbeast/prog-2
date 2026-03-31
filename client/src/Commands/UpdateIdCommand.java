package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;

public class UpdateIdCommand extends Command{
    public UpdateIdCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String arg){
        if (!cm.getIds().contains(Integer.valueOf(arg))){
            System.out.println("Такого айди нет в списке");
        }
        else {
            Movie m = cm.getMovie(Integer.valueOf(arg));
            cm.setMovie(Integer.valueOf(arg), m);
            CommandManager.addCommand("UpdateId");
            System.out.println("success");
        }
    }
}
