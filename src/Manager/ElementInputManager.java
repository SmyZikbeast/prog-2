package Manager;

import BaseFiles.Movie;
import Commands.Command;

import java.util.Scanner;

public class ElementInputManager {
    ElementInputManager(){};
    public Movie getMovie(){
        Validator v = new Validator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    name = sc.nextLine();
                } catch (NullPointerException e) {
                    name = null;
                }
            }
        } while (!v.validateName(name));
        return Movie(name, coordinates, oscarsCount, goldenPalmCount, usaBoxOffice, mpaaRating, screenwriter)
    }
}
