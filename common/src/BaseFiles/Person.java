package BaseFiles;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Base Person class for screenwriters
 *
 *
 *
 */
public class Person implements Comparable<Person>{
    @Expose
    private int id;
    @Expose
    private String name; //Поле не может быть null, Строка не может быть пустой
    DateTimeFormatter Dformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Expose
    private LocalDate birthday; //Поле не может быть null
    @Expose
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    @Expose
    private String passportID; //Длина строки должна быть не меньше 10, Поле может быть null
    @Expose
    private Country nationality; //Поле может быть null
    public Person(String name, LocalDate birthday, Double height, String passportID, Country nationality) {
        this.name = name;
        this.birthday = LocalDate.parse(birthday.format(Dformatter),Dformatter);
        this.height = height;
        this.passportID = passportID;
        this.nationality = nationality;
    }
    public Person(int id, String name, LocalDate birthday, Double height, String passportID, Country nationality) {
        this.id = id;
        this.name = name;
        this.birthday = LocalDate.parse(birthday.format(Dformatter),Dformatter);
        this.height = height;
        this.passportID = passportID;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return  "  name:" + name +
                "  birthday:" + birthday.format(Dformatter)+
                "  height:" + height+
                "  passportID:" + passportID +
                "  nationality:" + nationality;
    }
    /**
     * compares using person's height
     *
     *
     *
     */
    @Override
    public int compareTo(Person sw) {
        return this.height.compareTo(sw.height);
    }
    public Object getId() {return this.id;}
    public String toSql(){
        return "'"+name+"', '"+birthday.format(Dformatter)+"', "+height+", '"+passportID+"', '"+nationality+"'";
    }

    public void setId(int nextPersonId) {
        this.id = nextPersonId;
    }
}
