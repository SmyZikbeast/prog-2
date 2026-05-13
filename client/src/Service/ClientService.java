package Service;

import Manager.OutputManager;
import Response.Request;
import Response.Response;
import Utility.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientService {
    private volatile boolean running = true;
    private volatile boolean userState = false;
    private SocketChannel channel;
    private User user;
    public ClientService(){
        this.connect();
    }
    public boolean getUserState(){
        return this.userState;
    }
    public void connect() {
        new Thread(() -> {
        while (running) {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.connect(new InetSocketAddress("localhost", 13377));
                this.channel = channel;
                System.out.println("Connected to server!");
                this.run(channel);
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
    public void run(SocketChannel channel) {

    };
    public void authorize(User u) throws IOException {
        Response r = new Request("login", null, u).send(channel);
        this.userState = Boolean.parseBoolean(OutputManager.SerializeValue(r.getDataType(), r.getData()));
        if (userState){
            this.user = u;
        }
    };
    public void register(User u) throws IOException {
        Response r = new Request("register", null, u).send(channel);
        this.userState = Boolean.parseBoolean(OutputManager.SerializeValue(r.getDataType(), r.getData()));
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
}
