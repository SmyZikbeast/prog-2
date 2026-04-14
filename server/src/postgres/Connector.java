package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Demo";
        String uname = "s502792";
        String pass = "ymBEJ6rgxhY7wLM0";
        Connection con = DriverManager.getConnection(url, uname, pass);
        return con;
    }
}
