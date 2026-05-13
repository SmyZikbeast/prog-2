package Service;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import Response.Request;
import Response.Response;
import Utility.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class ClientService {
    private volatile boolean running = true;
    private volatile boolean userState = false;
    private MovieTableModel model;
    private SocketChannel channel;
    private User user;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public ClientService(){
        this.model = new MovieTableModel();
        this.connect();
        startAutoRefresh();
    }
    public boolean getUserState(){
        return this.userState;
    }
    private void startAutoRefresh() {
        new Thread(() -> {
            while (running) {
                try {
                    if (userState) {
                        refreshMovies();
                    }
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.out.println("refresh error: " + e.getMessage());
                }
            }
        }).start();
    }
    public void connect() {
        new Thread(() -> {
        while (running) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(new InetSocketAddress("localhost", 13377));
                this.channel = channel;
                System.out.println("Connected to server!");
                while(channel.isConnected()){}
            } catch (IOException e) {
                System.out.println(e.getMessage());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        }).start();
    }
    public void authorize(User u) throws IOException {
        Response r = new Request("login", null, u).send(channel);
        this.userState = (boolean) r.getData();
        if (userState){
            this.user = u;
        }
    };
    public void register(User u) throws IOException {
        Response r = new Request("register", null, u).send(channel);
        this.userState = (boolean) r.getData();
        if (userState){
            this.user = u;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public MovieTableModel getTableModel() {
        return model;
    }
    public void refreshMovies() {
        try {
            Response r = new Request("show", null, user).send(channel);
            List<Movie> movies =
                    ((List<?>) r.getData()).stream()
                            .map(obj -> gson.fromJson(
                                    gson.toJson(obj),
                                    Movie.class
                            ))
                            .toList();
            SwingUtilities.invokeLater(() -> {
                model.setMovies(movies);
            });
        } catch (Exception e) {
            System.out.println("refresh error: " + e.getMessage());
        }
    }
}
