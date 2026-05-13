package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Response.Response;

import java.util.Objects;

/**
 * helping class used for update id command
 *
 *
 *
 */
public class FindIdCommand extends Command{
    public FindIdCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public Response execute(){

            return new Response("String", "Found such ID");

    }
}
