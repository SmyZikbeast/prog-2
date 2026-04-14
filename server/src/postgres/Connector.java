package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String uname = "postgres";
        String pass = "uqCC<8977";
        Connection con = DriverManager.getConnection(url, uname, pass);
        System.out.println("postgres connected successfully");
        return con;
    }
}
