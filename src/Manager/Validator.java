package Manager;

import BaseFiles.Coordinates;
import BaseFiles.Country;
import BaseFiles.MpaaRating;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Validator {
    public boolean validateName(String name){
        return (name != null) && (!name.trim().isEmpty());
    }
    public boolean validateCoordinates(Coordinates c){
        return (c != null);
    }

    public boolean validateX(String x) {
        return (x != null) && (Double.valueOf(x)>-790);
    }
    public boolean validateY(String y) {
        return (y != null) && (Float.valueOf(y)>-716F);
    }
    public boolean validateOscarsCount(String oc){
        return (oc == null) || (Integer.valueOf(oc) > 0);
    }
    public boolean validateGPCount(String gpc){
        return (gpc == null) || (Long.valueOf(gpc) > 0);
    }
    public boolean validateUsaBoxOffice(String ubo){
        return (Integer.valueOf(ubo) > 0);
    }
    public boolean validateRating(String r){
        try {
            MpaaRating.valueOf(r);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
    public boolean validateBirthday(String bd){
        if (bd == null) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(bd, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public boolean validateHeight(String h){
        return (h!=null)&&(Double.valueOf(h)>0);
    }
    public boolean validatePassportID(String p){
        return (p == null) || (p.length()>10);
    }
    public boolean validateNationality(String n){
        if (n == null){
            return true;
        }
        try {
            Country.valueOf(n);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
