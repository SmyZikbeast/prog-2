package BaseFiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 10, Поле может быть null
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        return  "name:" + name + '\n' +
                "birthday:" + birthday.format(formatter) + "\n" +
                "height:" + height + "\n" +
                "passportID:" + passportID +  "\n" +
                "nationality:" + nationality;
    }
}
