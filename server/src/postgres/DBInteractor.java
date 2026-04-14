package postgres;

import java.sql.Connection;

public class DBInteractor {
    Connection con;

    public DBInteractor(Connection con) {
        this.con = con;
    }


}
