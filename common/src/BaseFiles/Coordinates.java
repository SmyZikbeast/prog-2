package BaseFiles;

import com.google.gson.annotations.Expose;
/**
 * Coordinates base class
 *
 *
 *
 */
public class Coordinates {
    @Expose
    public int id;
    @Expose
    private Double x; //Значение поля должно быть больше -790, Поле не может быть null
    @Expose
    private Float y; //Значение поля должно быть больше -716, Поле не может быть null

    public Coordinates(Double x, float y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(int id, Double x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "X:" + this.x + " Y:" + this.y;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public String toSql(){
        return x+", "+y;
    }

    public void setId(int Id) {
        this.id = Id;
    }
}
