import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.*;
import Commands.*;
import Manager.CollectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    public static void main(String[] args) {
        CollectionManager cm = new CollectionManager();
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help",new HelpCommand(cm));
        commandMap.put("info",new InfoCommand(cm));
        commandMap.put("show",new ShowCommand(cm));
        commandMap.put("add",new AddCommand(cm));
        commandMap.put("update",new UpdateIdCommand(cm));
        commandMap.put("remove_by_id",new RemoveByIdCommand(cm));
        commandMap.put("clear",new ClearCommand(cm));
        Scanner scanner = new Scanner(System.in);
        while(true){
            if (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    String[] tokens = (line.split(" "));
                    Command command = commandMap.get(tokens[0].toLowerCase());
                    if (tokens.length == 1) {
                        command.execute("");
                    } else if (tokens.length == 2) {
                        command.execute(tokens[1]);
                    }
                }
                catch (NullPointerException e){
                    System.out.println("некорректная команда");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }
}