package Commands;

import BaseFiles.Movie;
import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
import Comparator.CoordinatesComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ShowCommand extends Command{
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        CommandManager.addCommand("Show");
        Comparator<Movie> CoordinatesComparator = new CoordinatesComparator();
        return new Response("ObjectList",cm.getCollection().stream().sorted(CoordinatesComparator).collect(Collectors.toCollection(ArrayList::new)));
    }
}
