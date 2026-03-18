package Manager;

import Commands.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Runner {
    static String CollectionFile;

    public static void setCollectionFile(String collectionFile) {
        CollectionFile = collectionFile;
    }

    public static void start(InputStream stream, CollectionManager cm) throws IllegalArgumentException{
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand(cm));
        commandMap.put("info", new InfoCommand(cm));
        commandMap.put("show", new ShowCommand(cm));
        commandMap.put("add", new AddCommand(cm));
        commandMap.put("update", new UpdateIdCommand(cm));
        commandMap.put("remove_by_id", new RemoveByIdCommand(cm));
        commandMap.put("clear", new ClearCommand(cm));
        commandMap.put("save", new SaveCommand(cm));
        commandMap.put("exit", new ExitCommand(cm));
        commandMap.put("history", new HistoryCommand(cm));
        commandMap.put("add_if_max", new AddIfMaxCommand(cm));
        commandMap.put("add_if_min", new AddIfMinCommand(cm));
        commandMap.put("print_field_ascending_mpaa_rating", new PrintFieldAscendingMpaaRatingCommand(cm));
        commandMap.put("remove_any_by_usa_box_office", new RemoveAnyByUsaBoxOfficeCommand(cm));
        commandMap.put("count_less_than_screenwriter", new CountLessThanScreenwriterCommand(cm));
        commandMap.put("execute_script", new ExecuteScriptCommand(cm));
        Scanner scanner = new Scanner(stream);
        if (stream == System.in) {
            while (true) {
                try {
                    if (scanner.hasNextLine()) {
                        try {
                            String line = scanner.nextLine();
                            String[] tokens = (line.split(" "));
                            Command command = commandMap.get(tokens[0].toLowerCase());
                            if (tokens[0].equalsIgnoreCase("save")) {
                                command.execute(CollectionFile);
                            } else if (tokens.length == 1) {
                                command.execute();
                            } else if (tokens.length == 2) {
                                command.execute(tokens[1]);
                            }
                        } catch (NullPointerException e) {
                            System.out.println("некорректная команда");
                        } catch (IOException e) {
                            System.out.println("проблема с файлом");
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e){
                    System.out.println("не делай так больше");
                }
            }
        }
        else {
            while (scanner.hasNextLine()) {
                    try {
                        String line = scanner.nextLine();
                        String[] tokens = (line.split(" "));
                        Command command = commandMap.get(tokens[0].toLowerCase());
                        if (tokens[0].equalsIgnoreCase("save")) {
                            command.execute(CollectionFile);
                        } else if (tokens.length == 1) {
                            command.execute();
                        } else if (tokens.length == 2) {
                            command.execute(tokens[1]);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("некорректная команда");
                    } catch (IOException e) {
                        System.out.println("проблема с файлом");
                    }
            }
        }
    }
}
