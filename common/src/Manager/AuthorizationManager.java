package Manager;

import Response.Request;
import Response.Response;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AuthorizationManager {
    Scanner scanner;
    Gson mapper;
    SocketChannel channel;
    boolean f = false;

    public AuthorizationManager(Scanner scanner, Gson mapper, SocketChannel channel) {
        this.scanner = scanner;
        this.mapper = mapper;
        this.channel = channel;
    }

    public String[] authorize() throws NoSuchAlgorithmException, IOException {
        String username;
        String passwd;
        do {
            String way = null;
            do {
                System.out.println("register/login");
                way = scanner.nextLine();
            } while (!(way.equalsIgnoreCase("register") | way.equalsIgnoreCase("login")));
            System.out.println("user:");
            username = scanner.nextLine();
            System.out.println("passwd:");
            String password = scanner.nextLine();
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            passwd = hashBytes.toString();
            Request cmd = null;
            cmd = new Request(way.toLowerCase(), username, null, null, hashBytes, username);
            String json = mapper.toJson(cmd) + "\n";
            ByteBuffer buffer = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
            channel.write(buffer);
            ByteBuffer ReturnBuffer = ByteBuffer.allocate(8192);
            int bytesRead = channel.read(ReturnBuffer);
            if (bytesRead > 0) {
                ReturnBuffer.flip();
                String ResponseString = StandardCharsets.UTF_8.decode(ReturnBuffer).toString();
                Response response = mapper.fromJson(ResponseString, Response.class);
                String DataType = response.getDataType();
                Object Data = response.getData();
                System.out.println(OutputManager.SerializeValue(DataType, Data));
                if (OutputManager.SerializeValue(DataType, Data).equals("Register success") | OutputManager.SerializeValue(DataType, Data).equals("Login success")){
                    f = true;
                }
            }
        } while (!f);
        return new String[]{username,passwd};
    }
}
