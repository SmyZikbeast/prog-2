package BaseFiles;

import Utility.User;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main collection object class
 *
 *
 *
 */
public class Movie implements Comparable<Movie> {
    @Expose
    private int id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Expose
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Expose
    private Coordinates coordinates; //Поле не может быть null
    @Expose
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
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
    @Expose
    private User user;
    DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public Long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public void setGoldenPalmCount(Long goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public void setUsaBoxOffice(int usaBoxOffice) {
        this.usaBoxOffice = usaBoxOffice;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setScreenwriter(Person screenwriter) {
        this.screenwriter = screenwriter;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Movie(String name, Coordinates coordinates, Integer oscarsCount, Long goldenPalmCount, int usaBoxOffice, MpaaRating mpaaRating, Person screenwriter, User user) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.parse(LocalDateTime.now().format(dtformatter),dtformatter);
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.usaBoxOffice = usaBoxOffice;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
        this.user = user;
    }
    public Movie(int id, String name, Coordinates coordinates, Integer oscarsCount, Long goldenPalmCount, int usaBoxOffice, MpaaRating mpaaRating, Person screenwriter, User user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.parse(LocalDateTime.now().format(dtformatter),dtformatter);
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.usaBoxOffice = usaBoxOffice;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
        this.user = user;
    }
    public Movie(int id, String name, Coordinates coordinates, LocalDateTime creationDate, Integer oscarsCount, Long goldenPalmCount, int usaBoxOffice, MpaaRating mpaaRating, Person screenwriter, User user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.usaBoxOffice = usaBoxOffice;
        this.mpaaRating = mpaaRating;
        this.screenwriter = screenwriter;
        this.user = user;
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
        "  screen Writer:" + screenwriter +
        "  user: " + user;

    }
    @Override
    public int compareTo(Movie other){
        return Integer.compare(this.usaBoxOffice,other.usaBoxOffice);
    }

    public double getDistance() {
        return Math.sqrt(Math.pow(this.coordinates.getX(),2) - Math.pow(this.coordinates.getY(),2));
    }

    public String toSQL() {
        return ("'"+name+"', "+coordinates.id+", '"+creationDate.format(dtformatter)+"', "+oscarsCount+", "+goldenPalmCount+", "+usaBoxOffice+", '"+mpaaRating+"', "+ screenwriter.getId()+", '"+user.getId()+"'");
    }
    public String toSQLid() {
        return (id+", '"+name+"', "+coordinates.id+", '"+creationDate.format(dtformatter)+"', "+oscarsCount+", "+goldenPalmCount+", "+usaBoxOffice+", '"+mpaaRating+"', "+ screenwriter.getId()+", '"+user.getId()+"'");
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setPersonId(int nextPersonId) {
        this.screenwriter.setId(nextPersonId);
    }

    public void setCoordsId(int nextCoordsId) {
        this.coordinates.setId(nextCoordsId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUserId(int userId) {
        this.user.setId(userId);
    }
}