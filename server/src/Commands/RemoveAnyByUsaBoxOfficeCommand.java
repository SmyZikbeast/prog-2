package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class RemoveAnyByUsaBoxOfficeCommand extends Command{
    public RemoveAnyByUsaBoxOfficeCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.addCommand("RemoveAnyByUsaBoxOffice");
        Movie mv = cm.getCollection().stream().filter(s -> Integer.valueOf(arg) == s.getUsaBoxOffice()).findAny().orElse(null);
        if (mv != null){
            cm.removeId(mv.getId());
            return new Response("String", "Successfully removed");
        }
        return new Response("String", "Nothing removed");
    }
}
