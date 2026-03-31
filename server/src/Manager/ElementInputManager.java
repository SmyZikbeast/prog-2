package Manager;

import BaseFiles.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class ElementInputManager {
    public static Movie getMovie() {
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
        Coordinates coordinates;
        do {
        System.out.println("Введите координаты (X)");
        Double X;
        String sX = null;
        do {
            if (sc.hasNextLine()) {
                try {
                    sX = sc.nextLine();
                } catch (NullPointerException e) {
                    sX = null;
                }
            }
        } while (!v.validateX(sX));
        X = Double.valueOf(sX);

        System.out.println("Введите координаты (Y)");
        Float Y;
        String sY = null;
        do {
            if (sc.hasNextLine()) {
                try {
                    sY = sc.nextLine();
                } catch (NullPointerException e) {
                    sY = null;
                }
            }
        } while (!v.validateY(sY));
        Y = Float.valueOf(sY);
        coordinates = new Coordinates(X, Y);
        } while (!v.validateCoordinates(coordinates));
        System.out.println("Введите количество оскаров");
        String SoscarsCount = "";
        Integer oscarsCount = 0;
        do {
            if (sc.hasNextLine()) {
                try {
                    SoscarsCount = sc.nextLine();
                    oscarsCount = Integer.valueOf(SoscarsCount);
                } catch (NullPointerException | NumberFormatException e) {
                    if (SoscarsCount.isEmpty()) {
                        oscarsCount = null;
                    }
                }
            }
        } while (!v.validateRewardCount(SoscarsCount));
        System.out.println("Введите количество премий золотой пальмы");
        String SgoldenPalmCount = "";
        Long goldenPalmCount = 0L;
        do {
            if (sc.hasNextLine()) {
                try {
                    SgoldenPalmCount = sc.nextLine();
                    goldenPalmCount = Long.valueOf(SgoldenPalmCount);
                } catch (NullPointerException | NumberFormatException e) {
                    if (SgoldenPalmCount.length() == 0) {
                        goldenPalmCount = null;
                    }
                }
            }
        } while (!v.validateRewardCount(SgoldenPalmCount));
        System.out.println("Введите кассовые сборы в США");
        String SusaBoxOffice = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    SusaBoxOffice = sc.nextLine();
                } catch (NullPointerException e) {
                    SusaBoxOffice = null;
                }
            }
        } while (!v.validateUsaBoxOffice(SusaBoxOffice));
        int usaBoxOffice = Integer.valueOf(SusaBoxOffice);
        System.out.println("Введите рейтинг mpaa (G, PG, PG_13, R, NC_17)");
        String SmpaaRating = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    SmpaaRating = sc.nextLine();
                } catch (NullPointerException e) {
                    SmpaaRating = null;
                }
            }
        } while (!v.validateRating(SmpaaRating));
        MpaaRating mpaaRating = MpaaRating.valueOf(SmpaaRating);
        System.out.println("Про сценариста");
        System.out.println("Введите имя");
        String Name = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Name = sc.nextLine();
                } catch (NullPointerException e) {
                    Name = null;
                }
            }
        } while (!v.validateName(Name));
        System.out.println("Введите день рождения (DD-MM-YYYY)");
        String Sbirthday = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sbirthday = sc.nextLine().replaceAll("[^0-9]", "");;
                } catch (NullPointerException e) {
                    Sbirthday = null;
                }
            }
        } while (!v.validateBirthday(Sbirthday));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime Birthday = LocalDate.parse(Sbirthday, formatter).atStartOfDay();
        System.out.println("Введите рост");
        String Sheight = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sheight = sc.nextLine();
                } catch (NullPointerException e) {
                    Sheight = null;
                }
            }
        } while (!v.validateHeight(Sheight));
        Double Height = Double.valueOf(Sheight);
        System.out.println("Введите айди паспорта");
        String PassportID = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    PassportID = sc.nextLine();
                } catch (NullPointerException e) {
                    PassportID = null;
                }
                if (PassportID.length() == 0){
                    PassportID = null;
                }
            }
        } while (!v.validatePassportID(PassportID));
        System.out.println("Введите страну рождения (UNITED_KINGDOM, USA, FRANCE, INDIA)");
        String SCountry = "";
        Country Nationality = null;
        do {
            if (sc.hasNextLine()) {
                try {
                    SCountry = sc.nextLine();
                    if (SCountry.length() == 0){
                        Nationality = null;
                        SCountry = null;
                    }
                    else {
                        Nationality = Country.valueOf(SCountry);
                    }
                } catch (NullPointerException | IllegalArgumentException e) {
                    SCountry = null;
                }
            }
        } while (!v.validateNationality(SCountry));

        Person screenwriter = new Person(Name, Birthday, Height, PassportID, Nationality);
        System.out.println("success");
        return new Movie(name, coordinates, oscarsCount, goldenPalmCount, usaBoxOffice, mpaaRating, screenwriter);
    }
    public static Movie getMovie(int id) {
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
        Coordinates coordinates;
        do {
            System.out.println("Введите координаты (X)");
            Double X;
            String sX = null;
            do {
                if (sc.hasNextLine()) {
                    try {
                        sX = sc.nextLine();
                    } catch (NullPointerException e) {
                        sX = null;
                    }
                }
            } while (!v.validateX(sX));
            X = Double.valueOf(sX);

            System.out.println("Введите координаты (Y)");
            Float Y;
            String sY = null;
            do {
                if (sc.hasNextLine()) {
                    try {
                        sY = sc.nextLine();
                    } catch (NullPointerException e) {
                        sY = null;
                    }
                }
            } while (!v.validateY(sY));
            Y = Float.valueOf(sY);
            coordinates = new Coordinates(X, Y);
        } while (!v.validateCoordinates(coordinates));
        System.out.println("Введите количество оскаров");
        String SoscarsCount = "";
        Integer oscarsCount = 0;
        do {
            if (sc.hasNextLine()) {
                try {
                    SoscarsCount = sc.nextLine();
                    oscarsCount = Integer.valueOf(SoscarsCount);
                } catch (NullPointerException | NumberFormatException e) {
                    if (SoscarsCount.isEmpty()) {
                        oscarsCount = null;
                    }
                }
            }
        } while (!v.validateRewardCount(SoscarsCount));
        System.out.println("Введите количество премий золотой пальмы");
        String SgoldenPalmCount = "";
        Long goldenPalmCount = 0L;
        do {
            if (sc.hasNextLine()) {
                try {
                    SgoldenPalmCount = sc.nextLine();
                    goldenPalmCount = Long.valueOf(SgoldenPalmCount);
                } catch (NullPointerException | NumberFormatException e) {
                    if (SgoldenPalmCount.length() == 0) {
                        goldenPalmCount = null;
                    }
                }
            }
        } while (!v.validateRewardCount(SgoldenPalmCount));
        System.out.println("Введите кассовые сборы в США");
        String SusaBoxOffice = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    SusaBoxOffice = sc.nextLine();
                } catch (NullPointerException e) {
                    SusaBoxOffice = null;
                }
            }
        } while (!v.validateUsaBoxOffice(SusaBoxOffice));
        int usaBoxOffice = Integer.valueOf(SusaBoxOffice);
        System.out.println("Введите рейтинг mpaa (G, PG, PG_13, R, NC_17)");
        String SmpaaRating = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    SmpaaRating = sc.nextLine();
                } catch (NullPointerException e) {
                    SmpaaRating = null;
                }
            }
        } while (!v.validateRating(SmpaaRating));
        MpaaRating mpaaRating = MpaaRating.valueOf(SmpaaRating);
        System.out.println("Про сценариста");
        System.out.println("Введите имя");
        String Name = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Name = sc.nextLine();
                } catch (NullPointerException e) {
                    Name = null;
                }
            }
        } while (!v.validateName(Name));
        System.out.println("Введите день рождения (DD-MM-YYYY)");
        String Sbirthday = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sbirthday = sc.nextLine().replaceAll("[^0-9]", "");;
                } catch (NullPointerException e) {
                    Sbirthday = null;
                }
            }
        } while (!v.validateBirthday(Sbirthday));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime Birthday = LocalDate.parse(Sbirthday, formatter).atStartOfDay();
        System.out.println("Введите рост");
        String Sheight = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sheight = sc.nextLine();
                } catch (NullPointerException e) {
                    Sheight = null;
                }
            }
        } while (!v.validateHeight(Sheight));
        Double Height = Double.valueOf(Sheight);
        System.out.println("Введите айди паспорта");
        String PassportID = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    PassportID = sc.nextLine();
                } catch (NullPointerException e) {
                    PassportID = null;
                }
                if (PassportID.length() == 0){
                    PassportID = null;
                }
            }
        } while (!v.validatePassportID(PassportID));
        System.out.println("Введите страну рождения (UNITED_KINGDOM, USA, FRANCE, INDIA)");
        String SCountry = "";
        Country Nationality = null;
        do {
            if (sc.hasNextLine()) {
                try {
                    SCountry = sc.nextLine();
                    if (SCountry.length() == 0){
                        Nationality = null;
                        SCountry = null;
                    }
                    else {
                        Nationality = Country.valueOf(SCountry);
                    }
                } catch (NullPointerException | IllegalArgumentException e) {
                    SCountry = null;
                }
            }
        } while (!v.validateNationality(SCountry));

        Person screenwriter = new Person(Name, Birthday, Height, PassportID, Nationality);
        System.out.println("success");
        return new Movie(id, name, coordinates, oscarsCount, goldenPalmCount, usaBoxOffice, mpaaRating, screenwriter);
    }
    public static Person getPerson(){
        Validator v = new Validator();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя");
        String Name = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Name = sc.nextLine();
                } catch (NullPointerException e) {
                    Name = null;
                }
            }
        } while (!v.validateName(Name));
        System.out.println("Введите день рождения (DD-MM-YYYY)");
        String Sbirthday = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sbirthday = sc.nextLine().replaceAll("[^0-9]", "");;
                } catch (NullPointerException e) {
                    Sbirthday = null;
                }
            }
        } while (!v.validateBirthday(Sbirthday));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime Birthday = LocalDate.parse(Sbirthday, formatter).atStartOfDay();
        System.out.println("Введите рост");
        String Sheight = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    Sheight = sc.nextLine();
                } catch (NullPointerException e) {
                    Sheight = null;
                }
            }
        } while (!v.validateHeight(Sheight));
        Double Height = Double.valueOf(Sheight);
        System.out.println("Введите айди паспорта");
        String PassportID = "";
        do {
            if (sc.hasNextLine()) {
                try {
                    PassportID = sc.nextLine();
                } catch (NullPointerException e) {
                    PassportID = null;
                }
                if (PassportID.length() == 0){
                    PassportID = null;
                }
            }
        } while (!v.validatePassportID(PassportID));
        System.out.println("Введите страну рождения (UNITED_KINGDOM, USA, FRANCE, INDIA)");
        String SCountry = "";
        Country Nationality = null;
        do {
            if (sc.hasNextLine()) {
                try {
                    SCountry = sc.nextLine();
                    if (SCountry.length() == 0){
                        Nationality = null;
                        SCountry = null;
                    }
                    else {
                        Nationality = Country.valueOf(SCountry);
                    }
                } catch (NullPointerException | IllegalArgumentException e) {
                    SCountry = null;
                }
            }
        } while (!v.validateNationality(SCountry));

        Person screenwriter = new Person(Name, Birthday, Height, PassportID, Nationality);
        System.out.println("success");
        return screenwriter;
    }
}
