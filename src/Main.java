import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.*;
import Commands.*;
import Manager.CollectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String CollectionFile = args[0]+".json";
        CollectionManager cm = new CollectionManager();
        cm.load(CollectionFile);
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help",new HelpCommand(cm));
        commandMap.put("info",new InfoCommand(cm));
        commandMap.put("show",new ShowCommand(cm));
        commandMap.put("add",new AddCommand(cm));
        commandMap.put("update",new UpdateIdCommand(cm));
        commandMap.put("remove_by_id",new RemoveByIdCommand(cm));
        commandMap.put("clear",new ClearCommand(cm));
        commandMap.put("save",new SaveCommand(cm));
        commandMap.put("exit",new ExitCommand(cm));
        commandMap.put("add_if_max",new AddIfMaxCommand(cm));
        commandMap.put("add_if_min",new AddIfMinCommand(cm));


        Scanner scanner = new Scanner(System.in);
        while(true){
            if (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    String[] tokens = (line.split(" "));
                    Command command = commandMap.get(tokens[0].toLowerCase());
                    if (tokens[0].equalsIgnoreCase("save")){
                        command.execute(CollectionFile);
                    }
                    else if (tokens.length == 1) {
                        command.execute();
                    }
                    else if (tokens.length == 2) {
                        command.execute(tokens[1]);
                    }
                }
                catch (NullPointerException e){
                    System.out.println("некорректная команда");
                } catch (IOException e) {
                    System.out.println("проблема с файлом");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}