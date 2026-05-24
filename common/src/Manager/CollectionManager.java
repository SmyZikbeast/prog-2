package Manager;

import Adapters.*;
import BaseFiles.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import postgres.DBInteractor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.stream.Collectors.toCollection;

/**
 * class that manages collection of movies
 *
 *
 *
 */
public class CollectionManager {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    String Type = "LinkedList";
    LinkedList<Movie> collection = new LinkedList<>();
    LocalDateTime InitDate;

    public DBInteractor getInteractor() {
        return interactor;
    }

    DBInteractor interactor;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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
        lock.readLock().lock();
        try {
            return collection.size();
        }
        catch (NullPointerException e){
            return 0;
        }
        finally {
            lock.readLock().unlock();
        }
    }
    public LinkedList<Movie> getCollection(){
        lock.readLock().lock();
        try {
            return this.collection;
        }
        finally {
            lock.readLock().unlock();
        }
    }
    public void addMovie(Movie m) throws SQLException {
        lock.writeLock().lock();
        try {
            interactor.addMovie(m, m.getScreenwriter(), m.getCoordinates(), m.getUser());
            this.load();
        } finally {
            lock.writeLock().unlock();
        }
    }
    public boolean setMovie(Movie m) throws SQLException {
        lock.writeLock().lock();
        try {
            boolean f = interactor.removeId(m.getId());
            interactor.addMovie(m, m.getScreenwriter(), m.getCoordinates(), m.getId(), m.getUser());
            this.load();
            return f;
        }
        finally {
            lock.writeLock().unlock();
        }
    }
    public ArrayList<Integer> getIds(){
        return collection.stream().map(o -> o.getId()).collect(toCollection(ArrayList::new));
    }
    public boolean removeId(Integer id, String username) throws SQLException{
        if (!interactor.matchesId(id,username)){
            return false;
        }
        lock.writeLock().lock();
        try {
            boolean f = interactor.removeId(id);
            this.load();
            return f;
        }
        finally {
            lock.writeLock().unlock();
        }
    }
    public void clear() throws SQLException {
        lock.writeLock().lock();
        try {
            collection.clear();
            interactor.clear();
        }
        finally {
            lock.writeLock().unlock();
        }
    }
    public void load() throws SQLException {
        this.collection = interactor.getMovies();
    }
    public void setCollection(LinkedList<Movie> collection) {
        lock.writeLock().lock();
        try {
            this.collection = collection;
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    public Movie getMovie(int arg) {
        lock.readLock().lock();
        try {
            return collection.stream().filter(o -> o.getId() == arg).findFirst().orElse(null);
        }
        finally {
            lock.readLock().unlock();
        }
    }
    public boolean findId(Movie m) throws SQLException {
        return interactor.findId(m);
    }
}