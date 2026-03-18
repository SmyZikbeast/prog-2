package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

public class HistoryCommand extends Command{
    HistoryCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        CommandManager.addCommand("history");
        for (String s : CommandManager.getHistory()){
            System.out.println(s);
        }
    }

}
