package Manager;

import BaseFiles.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * class that turns values from server to string
 *
 *
 *
 */
public class OutputManager {
    public static String SerializeValue(String Dt, Object Data){
        return (String) switch (Dt){
            case "String"-> Data;
            case "Movie" -> ((Movie) Data).toString();
            case "ObjectList" -> ((ArrayList) Data).stream().map(s -> s.toString()).collect(Collectors.joining(", \n", "[", "]"));
            case "StringList" -> ((ArrayList)Data).stream().collect(Collectors.joining(", \n", "[", "]"));
            case "Integer" -> "Found Such ID";
            default -> throw new IllegalStateException("Unexpected value: " + Dt);
        };
    }
}
