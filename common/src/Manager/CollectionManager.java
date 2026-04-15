package Manager;

import Adapters.*;
import BaseFiles.*;
import Response.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import postgres.DBInteractor;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.stream.Collectors.toCollection;

/**
 * class that manages collection of movies
 *
 *
 *
 */
public class CollectionManager {
    String Type = "LinkedList";
    LinkedList<Movie> collection = new LinkedList<>();
    LocalDateTime InitDate;
    DBInteractor interactor;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public CollectionManager(DBInteractor interactor){
        this.InitDate  = LocalDateTime.now();
        this.interactor = interactor;
    }

    public LocalDateTime getInitDate(){
        return InitDate;
    }
    public String getType(){
        return this.Type;
    }
    public int getElementsAmount(){
        try {
            return collection.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }
    public LinkedList<Movie> getCollection(){
        return this.collection;
    }
    public void addMovie(Movie m) throws SQLException {
        interactor.addMovie(m, m.getScreenwriter(), m.getCoordinates());
        this.load();
    }
    public boolean setMovie(int id, Movie m) throws SQLException {
        boolean f = interactor.removeId(id);
        interactor.addMovie(m, m.getScreenwriter(),m.getCoordinates(), id);
        this.load();
        return f;
    }
    public ArrayList<Integer> getIds(){
        return collection.stream().map(o -> o.getId()).collect(toCollection(ArrayList::new));
    }
    public boolean removeId(Integer id) throws SQLException{
        boolean f = interactor.removeId(id);
        this.load();
        return f;
    }
    public void clear() throws SQLException {
        collection.clear();
        interactor.clear();
    }
    public void load() throws SQLException {
        this.collection = interactor.getMovies();
    }
    public void setCollection(LinkedList<Movie> collection) {
        this.collection = collection;
    }

    public Movie getMovie(int arg) {
        return collection.stream().filter(o -> o.getId() == arg).findFirst().orElse(null);
    }
}