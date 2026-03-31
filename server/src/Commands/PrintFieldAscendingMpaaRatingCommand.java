package Commands;

import BaseFiles.MpaaRating;
import Manager.CollectionManager;
import Manager.CommandManager;

import java.util.Collections;
import java.util.List;


public class PrintFieldAscendingMpaaRatingCommand extends Command{
    public PrintFieldAscendingMpaaRatingCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        List<MpaaRating> ratings = cm.getMpaaRatings();
        Collections.sort(ratings);
        CommandManager.addCommand("PrintFieldAscendingMpaaRating");
        System.out.println(ratings);
    }
}
