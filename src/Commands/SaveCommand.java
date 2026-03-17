package Commands;

import Manager.CollectionManager;

import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(String path) throws IOException {
        cm.save(path);
    }
}
