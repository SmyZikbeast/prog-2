package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String path) throws IOException {
        cm.save(path);
        CommandManager.addCommand("Save");
    }
}
