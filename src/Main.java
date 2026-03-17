import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.*;
import Commands.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Movie> collection = new LinkedList<>();
        collection.add(new Movie("Escape"));
        collection.add(new Movie("Return"));

        // Создаем Gson с адаптером для LocalDateTime
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .create();

        System.out.println("Gson успешно подключен!");

        // Сериализация всей коллекции
        String json = gson.toJson(collection);
        System.out.println("JSON коллекции:");
        System.out.println(json);


        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help",new HelpCommand());
        Scanner scanner = new Scanner(System.in);
        while(true){
            if (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(" ");
                    Command command = commandMap.get(tokens[0]);
                    command.execute();
                }
                catch (NullPointerException e){
                    System.out.println("бро ты умрешь и тд");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}