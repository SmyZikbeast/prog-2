package BaseFiles;

public class Movie {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private Long goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    private int usaBoxOffice; //Значение поля должно быть больше 0
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person screenwriter;
}