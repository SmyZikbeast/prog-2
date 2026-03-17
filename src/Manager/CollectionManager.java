package Manager;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;

public class CollectionManager {
    String Type = "LinkedList";
    LinkedList<Movie> collection = new LinkedList<>();;
    public CollectionManager(){
        LocalDateTime InitDate = LocalDateTime.now();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .create();
        System.out.println("Gson connection success");
    }
    void fill() {
        collection.add(new Movie("Escape"));
        collection.add(new Movie("Return"));
    }
    void f(){

    }
    public String getType(){
        return this.Type;
    }
}