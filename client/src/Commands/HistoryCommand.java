package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class HistoryCommand extends Command{
    public HistoryCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.addCommand("history");
        for (String s : CommandManager.getHistory()){
            System.out.println(s);
        }
        return null;
    }

}
