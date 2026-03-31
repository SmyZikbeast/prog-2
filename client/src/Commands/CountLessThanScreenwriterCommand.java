package Commands;

import BaseFiles.Movie;
import BaseFiles.Person;
import Manager.CollectionManager;
import Manager.CommandManager;
import Manager.ElementInputManager;
import Response.Response;

public class CountLessThanScreenwriterCommand extends Command{
    public CountLessThanScreenwriterCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        int c = 0;
        Person p = ElementInputManager.getPerson();
        for (Movie m:cm.getCollection()){
            Person sw = m.getScreenwriter();
            if (p.compareTo(sw) > 0) {
                c += 1;
            }
        }
        System.out.println("Человек меньше чем этот:"+c);
        CommandManager.addCommand("CountLessThanScreenwriter");
        System.out.println("success");
        return null;
    }
}
