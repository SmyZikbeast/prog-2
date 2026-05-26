package ui;

import java.util.LinkedList;
import java.util.List;

public class historyManager {
    LinkedList<String> history = new LinkedList<>();
    public void add(String s){
        history.add(s);
    }
    public List<String> getHistory(){
        return history;
    }
}
