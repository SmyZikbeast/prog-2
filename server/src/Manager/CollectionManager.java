package Manager;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import BaseFiles.MpaaRating;
import Response.Response;
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
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
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
    public void addMovie(Movie m){
        collection.add(m);
    }
    public void setMovie(int id, Movie m){
        collection = collection.stream()
                .map(o -> o.getId() == id ? m : o).collect(toCollection(LinkedList::new));
    }
    public ArrayList<Integer> getIds(){
        return collection.stream().map(o -> o.getId()).collect(toCollection(ArrayList::new));
    }

    public void removeId(Integer id) {
        collection = collection.stream().filter(c -> c.getId()!=id).collect(toCollection(LinkedList::new));
    }

    public void clear(){
        collection.clear();
    }
    public void load(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        if (inputStream.available() > 0){
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Type collectionType = new TypeToken<LinkedList<Movie>>(){}.getType();
            this.collection = gson.fromJson(reader, collectionType);
            reader.close();
            if (!collection.isEmpty()) {
                Movie.setNextId(this.getIds().getLast());
                System.out.println("Collection loaded from file");
            }
        }
        else {
            System.out.println("Source File empty");
        }
    }
    public void save(String path) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_8)) {
            gson.toJson(collection, writer);
        }
    }


    public void setCollection(LinkedList<Movie> collection) {
        this.collection = collection;
    }
}