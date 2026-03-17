package Manager;

import BaseFiles.Coordinates;
import BaseFiles.Country;
import BaseFiles.MpaaRating;

import java.time.ZonedDateTime;

public class Validator {
    public boolean validateName(String name){
        return (name != null) && (!name.isEmpty());
    }
    public boolean validateCoordinates(Coordinates c){
        return (c != null);
    }

    public boolean validateX(Double x) {
        return (x != null) && (x>-790);
    }
    public boolean validateY(Float y) {
        return (y != null) && (y>-716F);
    }
    public boolean validateOscarsCount(Integer oc){
        return (oc == null) || (oc > 0);
    }
    public boolean validateGPCount(Long gpc){
        return (gpc == null) || (gpc > 0);
    }
    public boolean usaBoxOffice(int ubo){
        return (ubo > 0);
    }
    public boolean validateRating(String r){
        try {
            MpaaRating.valueOf(r);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
    public boolean validateBirthday(ZonedDateTime bd){
        return bd!=null;
    }
    public boolean validateHeight(Double h){
        return (h!=null)&&(h>0);
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
