package Manager;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class CollectionManager {
    String Type = "LinkedList";
    LinkedList<Movie> collection = new LinkedList<>();
    LocalDateTime InitDate;
    public CollectionManager(){
        this.InitDate  = LocalDateTime.now();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .create();
    }

    public LocalDateTime getInitDate(){
        return InitDate;
    }
    public String getType(){
        return this.Type;
    }
    public int getElementsAmount(){
        return collection.size();
    }
    public LinkedList<Movie> getCollection(){
        return this.collection;
    }
    public Movie getMovie(){
        return ElementInputManager.getMovie();
    }
    public void addMovie(Movie m){
        collection.add(m);
    }
    public void setMovie(int id, Movie m){
        for (int i = 0; i < collection.size(); i++){
            if (collection.get(i).getId() == id){
                collection.set(i, m);
                break;
            }
        }
    }
    public ArrayList<Integer> getIds(){
        ArrayList<Integer> IDs = new ArrayList<>();
        for (Movie mv : collection){
            IDs.add(mv.getId());
        }
        return IDs;
    }

    public void removeId(Integer id) {
        for (int i = 0; i < collection.size(); i++){
            if (collection.get(i).getId() == id){
                collection.remove(i);
                break;
            }
        }
    }

    public void clear(){
        collection.clear();
    }
}