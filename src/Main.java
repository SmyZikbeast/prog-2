import Commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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