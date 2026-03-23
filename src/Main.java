import BaseFiles.*;
import Commands.*;
import Manager.CollectionManager;
import Manager.Runner;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String CollectionFile = args[0]+".json";
        Path filePath = Paths.get(CollectionFile);
        try{
            Files.createFile(filePath);
            System.out.println("File created");
        }
        catch (FileAlreadyExistsException e){
            System.out.println("File loaded");
        }
        CollectionManager cm = new CollectionManager();
        cm.load(CollectionFile);

        Runner.setCollectionFile(CollectionFile);
        Runner.start(System.in, cm);
    }
}