package BaseFiles;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
/**
 * Main collection object class
 *
 *
 *
 */
public class Movie implements Comparable<Movie> {
    private static int Nextid = 0;
    @Expose
    private int id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Expose
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Expose
    private Coordinates coordinates; //Поле не может быть null
    @Expose
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Expose
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    @Expose
    private Long goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    @Expose
    private int usaBoxOffice; //Значение поля должно быть больше 0
    @Expose
    private MpaaRating mpaaRating; //Поле не может быть null
    @Expose
    private Person screenwriter;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Movie(String name, Coordinates coordinates, Integer oscarsCount, Long goldenPalmCount, int usaBoxOffice, MpaaRating mpaaRating, Person screenwriter) {
        Nextid++;
        this.id = Nextid;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.parse(LocalDateTime.now().format(formatter),formatter);
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.usaBoxOffice = usaBoxOffice;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
    }
    public Movie(int id, String name, Coordinates coordinates, Integer oscarsCount, Long goldenPalmCount, int usaBoxOffice, MpaaRating mpaaRating, Person screenwriter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.parse(LocalDateTime.now().format(formatter),formatter);
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.usaBoxOffice = usaBoxOffice;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
    }
    public static void setNextId(int id){
        Movie.Nextid = id;
    }
    public int getId() {
        return id;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public Person getScreenwriter() {
        return screenwriter;
    }

    public int getUsaBoxOffice() {
        return usaBoxOffice;
    }

    @Override
    public String toString(){
         return "id:" + id +
        "  name:" + name +
        "  coordinates:" + coordinates +
        "  creation Date:" + creationDate +
        "  oscars Count:" + oscarsCount +
        "  golden Palm Count:" + goldenPalmCount +
        "  usa Box Office:" + usaBoxOffice +
        "  mpaa Rating:" + mpaaRating +
        "  screen Writer:" + screenwriter;
    }
    @Override
    public int compareTo(Movie other){
        return Integer.compare(this.usaBoxOffice,other.usaBoxOffice);
    }

public double getCoordinates() {
        return Math.sqrt(Math.pow(this.coordinates.getX(),2) - Math.pow(this.coordinates.getY(),2));
}}