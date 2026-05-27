package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * used to remove first element by its usaboxoffice
 *
 *
 *
 */
public class RemoveAnyByUsaBoxOfficeCommand extends Command{
    public RemoveAnyByUsaBoxOfficeCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute() throws SQLException, IOException {
        Movie mv = cm.getCollection().stream().filter(s -> Integer.parseInt(arg.toString().substring(0,arg.toString().length()-2)) == s.getUsaBoxOffice()).findAny().orElse(null);
        if (mv != null){
            cm.removeId(mv.getId(), mv.getUser().getUsername());
            return new Response("String", "Successfully removed");
        }
        return new Response("String", "Nothing removed");
    }
}
