package Manager;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static ArrayList<String> commands = new ArrayList<>();
    public static void addCommand(String c){
        commands.add(c);
    }
    public static List<String> getHistory(){
        if (commands.isEmpty()){
            return new ArrayList<>();
        }
        int size = commands.size();
        return commands.subList(Math.max(0, size-9), size);
    }
    public static void clear(){
        CommandManager.commands.clear();
    }
}
