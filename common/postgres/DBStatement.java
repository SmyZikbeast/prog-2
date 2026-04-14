package postgres;

import BaseFiles.Movie;

public class DBStatement {
    public static String CreateStatement(String type, Movie movie) {
        String sql = null;
        if (type == "add") {
            sql = "INSERT INTO MOVIES(NAME, COORDINATES_ID, CREATION_DATE, OSCARS_COUNT, GOLDEN_PALM_COUNT, USA_BOX_OFFICE, MPAA_RATING, SCREENWRITER_ID) VALUES(" + movie.toSQL() + ")";
        }
        return sql;
    }

}
