package BaseFiles;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 10, Поле может быть null
    private Country nationality; //Поле может быть null
}
