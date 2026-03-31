package BaseFiles;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Person implements Comparable<Person>{
    @Expose
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Expose
    private java.time.LocalDateTime birthday; //Поле не может быть null
    @Expose
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    @Expose
    private String passportID; //Длина строки должна быть не меньше 10, Поле может быть null
    @Expose
    private Country nationality; //Поле может быть null
    public Person(String name, LocalDateTime birthday, Double height, String passportID, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return  "name:" + name + '\n' +
                "birthday:" + birthday + "\n" +
                "height:" + height + "\n" +
                "passportID:" + passportID +  "\n" +
                "nationality:" + nationality;
    }
    @Override
    public int compareTo(Person sw) {
        return this.height.compareTo(sw.height);
    }
}
