package BaseFiles;

import java.time.LocalDateTime;

public class Movie {
    private static int Nextid = 1;
    private int id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private Long goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    private int usaBoxOffice; //Значение поля должно быть больше 0
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person screenwriter;
     public Movie(String name) {
        this.id = Nextid;
        Movie.Nextid+=1;
        this.name = name;
        this.coordinates = new Coordinates(1.0, 1.0F);
        this.creationDate = LocalDateTime.now();
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
        "screen Writer:" + screenwriter + "\n";
    }
}