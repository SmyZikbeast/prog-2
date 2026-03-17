package Manager;

import BaseFiles.Coordinates;
import BaseFiles.Country;
import BaseFiles.MpaaRating;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Validator {
    public boolean validateName(String name){
        return (name != null) && (!name.trim().isEmpty()) && name.matches("[a-zA-Zа-яА-ЯёЁ]+");
    }
    public boolean validateCoordinates(Coordinates c){
        return (c != null);
    }

    public boolean validateX(String x) {
        try {
            return (x != null) && (Double.valueOf(x)>-790);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public boolean validateY(String y) {
        try {
            return (y != null) && (Float.valueOf(y)>-716F);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public boolean validateOscarsCount(String oc){
        try {
            return (oc == null) || (Integer.valueOf(oc) > 0);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public boolean validateGPCount(String gpc){
        try {
            return (gpc == null) || (Long.valueOf(gpc) > 0);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public boolean validateUsaBoxOffice(String ubo){
        try {
            return (Integer.valueOf(ubo) > 0);
        }
        catch (NumberFormatException e){
            return false;
        }
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu");
            LocalDate.parse(bd.trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public boolean validateHeight(String h){
        try {
            return (h!=null)&&(Double.valueOf(h)>0);
        }
        catch (NumberFormatException e){
            return false;
        }
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
