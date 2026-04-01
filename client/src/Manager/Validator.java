package Manager;

import BaseFiles.Coordinates;
import BaseFiles.Country;
import BaseFiles.MpaaRating;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * class that validates all values separately
 *
 *
 *
 */
public class Validator {
    public boolean validateName(String name){
        if ((name != null) && (!name.trim().isEmpty()) && name.matches("[a-zA-Zа-яА-ЯёЁ]+")){
        return true;}
        else{
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateCoordinates(Coordinates c){

        if (c != null){
            return true;
        }
        else {
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }

    public boolean validateX(String x) {
        try {
            if( (x != null) && (Double.valueOf(x)>-790)){
                return true;
            }
            else {
                System.out.println("неправильно, попробуй еще раз");
                return false;
            }
        }
        catch (NumberFormatException e){
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateY(String y) {
        try {
            if( (y != null) && (Float.valueOf(y)>-716F)) {
                return true;
            }
            else {
                System.out.println("неправильно, попробуй еще раз");
                return false;
            }
        }
        catch (NumberFormatException e){
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateRewardCount(String oc){
        if (oc.length()==0){
            return true;
        }
        try {
            return (Integer.valueOf(oc) > 0);
        }
        catch (NumberFormatException e){
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }

    public boolean validateUsaBoxOffice(String ubo){
        try {
            if(Integer.valueOf(ubo) > 0){
                return true;
            }
            else {
                System.out.println("неправильно, попробуй еще раз");
                return false;
            }
        }
        catch (NumberFormatException e){
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateRating(String r){
        try {
            MpaaRating.valueOf(r);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateBirthday(String bd){
        if (bd == null) {
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu");
            LocalDate date = LocalDate.parse(bd.trim(), formatter);
            if (!date.isAfter(LocalDate.now())){
                return true;
            }
            else{
                System.out.println("дата после сегодняшней");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateHeight(String h){
        try {
            if ((h!=null)&&(Double.valueOf(h)>0)) {
                return true;
            }
            else{
                System.out.println("неправильно, попробуй еще раз");
                return false;
            }
        }
        catch (NumberFormatException e){
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validatePassportID(String p){
        if( (p == null) || (p.length()>10)){
            return true;
        }
        else{
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
    public boolean validateNationality(String n){
        if (n == null){
            return true;
        }
        try {
            Country.valueOf(n);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("неправильно, попробуй еще раз");
            return false;
        }
    }
}
