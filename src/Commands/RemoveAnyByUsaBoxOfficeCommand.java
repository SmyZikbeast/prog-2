package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;

public class RemoveAnyByUsaBoxOfficeCommand extends Command{
    public RemoveAnyByUsaBoxOfficeCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String arg){
        for (Movie m: cm.getCollection()){
            if (m.getUsaBoxOffice() == Integer.valueOf(arg)) {
                cm.removeId(m.getId());
                break;
            }
        }
        CommandManager.addCommand("RemoveAnyByUsaBoxOffice");
    }
}
