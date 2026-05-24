package Response;

import Adapters.LocalDateAdapter;
import Adapters.LocalDateTimeAdapter;
import Utility.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * class used to send objects from client to server
 *
 *
 *
 */
public class Request {
    @Expose
    PacketType packetType = PacketType.COMMAND;
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
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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
    public void setPacketType(PacketType pt){
        this.packetType = pt;
    }
    public PacketType getPacketType(){
        return this.packetType;
    }
    public Response send(SocketChannel channel) throws IOException {
        String json = mapper.toJson(this, Request.class) + "\n";
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
    @Override
    public String toString(){
        return "packettype:" + this.getPacketType() + " type:"+this.getType()+ " arg:"+this.getArg()+" user:"+this.getUser();
    }
}
