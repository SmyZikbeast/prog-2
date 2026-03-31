package BaseFiles;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movie implements Comparable<Movie> {
    private static int Nextid = 1;
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
         return "id:" + id + "\n" +
        "name:" + name + "\n" +
        "coordinates:" + coordinates + "\n" +
        "creation Date:" + creationDate + "\n" +
        "oscars Count:" + oscarsCount + "\n" +
        "golden Palm Count:" + goldenPalmCount + "\n" +
        "usa Box Office:" + usaBoxOffice + "\n" +
        "mpaa Rating:" + mpaaRating + "\n" +
        "screen Writer: \n" + screenwriter;
    }
    @Override
    public int compareTo(Movie other){
        return Integer.compare(this.usaBoxOffice,other.usaBoxOffice);
    }
}