package Service;

import Adapters.LocalDateAdapter;
import Adapters.LocalDateTimeAdapter;
import BaseFiles.Movie;
import Response.Request;
import Response.Response;
import Swing.MovieTableModel;
import Utility.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Response.PacketType;
import ui.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ClientService {
    private volatile boolean running = true;
    private volatile boolean userState = false;
    private MovieTableModel model;
    private SocketChannel channel;
    private User user;
    private MainFrame mainFrame;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public ClientService() throws InterruptedException {
        this.model = new MovieTableModel();
        this.connect();
        startAutoConnect();
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
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("refresh error: " + e.getMessage());
                }
            }
        }).start();
    }
    private void startAutoConnect(){
        Request req = new Request(null,null,user);
        req.setPacketType(PacketType.PING);
        new Thread(() -> {
            while (running && this.channel !=null){
                try {
                    req.setUser(user);
                    Response r = req.send(channel);
                    Thread.sleep(1000);
                } catch (IOException e) {
                    try {
                        Thread.sleep(500);
                        this.connect();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void connect() throws InterruptedException {
        while (this.channel == null) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(new InetSocketAddress("localhost", 13377));
                this.channel = channel;
                System.out.println("Connected to server!");

            } catch (IOException e) {
                System.out.println(e.getMessage());
                Thread.sleep(100);
            }
        }
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
    public void updateMovie(Movie movie) {
        try {
            Request req = new Request("update", movie, this.getUser());
            Response r = req.send(channel);
            if (r != null) {
                refreshMovies();
            }
        } catch (Exception e) {
            System.out.println("update failed: " + e.getMessage());
        }
    }
    public void deleteMovie(int id) throws IOException {
        Response r = new Request("remove", id, this.getUser()).send(channel);
    }
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }
}
