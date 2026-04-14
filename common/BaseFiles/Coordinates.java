package BaseFiles;

import com.google.gson.annotations.Expose;
/**
 * Coordinates base class
 *
 *
 *
 */
public class Coordinates {
    public Double getX() {
    return x;
}public Float getY() {
    return y;
}@Expose
    private Double x; //Значение поля должно быть больше -790, Поле не может быть null
    @Expose
    private Float y; //Значение поля должно быть больше -716, Поле не может быть null
    public Coordinates(Double x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "X:"+this.x+" Y:"+this.y;
    }
}
