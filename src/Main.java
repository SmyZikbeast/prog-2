import BaseFiles.*;
import Commands.*;
import Manager.CollectionManager;
import Manager.Runner;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        String CollectionFile = args[0]+".json";
        CollectionManager cm = new CollectionManager();
        cm.load(CollectionFile);
        Runner.setCollectionFile(CollectionFile);
        Runner.start(System.in, cm);
    }
}