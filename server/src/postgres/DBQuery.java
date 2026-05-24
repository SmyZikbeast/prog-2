package postgres;

import BaseFiles.Coordinates;
import BaseFiles.Movie;
import BaseFiles.Person;

public class DBQuery {
    public static String CreateQuery(String type, Object o) {
        String sql = null;
        if (type == "add_movie") {
            sql = "INSERT INTO MOVIES(NAME, COORDINATES_ID, CREATION_DATE, OSCARS_COUNT, GOLDEN_PALM_COUNT, USA_BOX_OFFICE, MPAA_RATING, PERSON_ID, USER_ID) VALUES(?,?,?,?,?,?,?,?,?)";
        } else if (type == "add_coords") {
            sql = "INSERT INTO COORDINATES(X, Y) VALUES (?,?)";
        } else if (type == "add_person") {
            sql = "INSERT INTO PERSON(NAME, BIRTHDAY, HEIGHT, PASSPORT_ID, NATIONALITY) VALUES (?,?,?,?,?)";
        } else if (type == "add_movie_id") {
            sql = "INSERT INTO MOVIES(ID, NAME, COORDINATES_ID, CREATION_DATE, OSCARS_COUNT, GOLDEN_PALM_COUNT, USA_BOX_OFFICE, MPAA_RATING, PERSON_ID, USER_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";
        }
        return sql;
    }
}
