package Manager;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class CollectionManager {
    String Type = "LinkedList";
    LinkedList<Movie> collection = new LinkedList<>();
    LocalDateTime InitDate;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public CollectionManager(){
        this.InitDate  = LocalDateTime.now();
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
    public void load(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        Type collectionType = new TypeToken<LinkedList<Movie>>(){}.getType();
        collection = gson.fromJson(reader, collectionType);
        reader.close();
        if (!collection.isEmpty()) {
            Movie.setNextId(this.getIds().getLast());
        }
        System.out.println(collection);
    }
    public void save(String path) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_8)) {
            gson.toJson(collection, writer);
        }
    }
}