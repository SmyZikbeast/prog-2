package Response;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.*;
import Utility.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * class used to send objects from client to server
 *
 *
 *
 */
public class Request {
    @Expose
    String type;
    @Expose
    Object arg;
    @Expose
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    Gson mapper = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Request(String type, Object arg, User user){
        this.type = type;
        this.arg = arg;
        this.user = user;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public Response send(SocketChannel channel) throws IOException {
        String json = mapper.toJson(this, Request.class) + "\n";
        System.out.println(json);
        ByteBuffer buffer = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
        ByteBuffer returnBuffer = ByteBuffer.allocate(8192);
        int bytesRead = channel.read(returnBuffer);
        if (bytesRead == -1) {
            throw new IOException("Server closed connection");
        }
        returnBuffer.flip();
        String responseJson = StandardCharsets.UTF_8
                .decode(returnBuffer)
                .toString();
        return mapper.fromJson(responseJson, Response.class);
    }
}
