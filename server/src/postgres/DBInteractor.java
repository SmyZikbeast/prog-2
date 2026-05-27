package postgres;

import BaseFiles.*;
import Response.Request;
import Utility.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


public class DBInteractor {
    Connection con;
    DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    DateTimeFormatter dtformatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public DBInteractor(Connection con) throws SQLException {
        this.con = con;
    }
    public LinkedList<Movie> getMovies() throws SQLException {

        LinkedList<Movie> Movies = new LinkedList<>();
        Statement st = con.createStatement();
        Statement st2 = con.createStatement();
        Statement st3 = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM MOVIES;");
        while (rs.next()){
            int Id = rs.getInt("ID");
            String Name = rs.getString("NAME");
            int CoordsId = rs.getInt("COORDINATES_ID");
            String CreationDate = rs.getString("CREATION_DATE");
            Integer OscarsCount = rs.getInt("OSCARS_COUNT");
            Long GoldenPalmCount = rs.getLong("GOLDEN_PALM_COUNT");
            int UsaBoxOffice = rs.getInt("USA_BOX_OFFICE");
            MpaaRating mpaaRating = MpaaRating.valueOf(rs.getString("MPAA_RATING"));
            int PersonId = rs.getInt("PERSON_ID");
            int UserId = rs.getInt("USER_ID");
            ResultSet rsC = st2.executeQuery(String.format("SELECT * FROM COORDINATES WHERE ID = %s;",CoordsId));
            rsC.next();
            double X = rsC.getDouble("X");
            float Y = rsC.getFloat("Y");
            ResultSet rsS = st3.executeQuery(String.format("SELECT * FROM PERSON WHERE ID = %s",PersonId));
            rsS.next();
            String Scname = rsS.getString(2);
            LocalDate birthday = rsS.getObject(3, LocalDate.class);
            Double height = rsS.getDouble(4);
            String passportID = rsS.getString(5);
            Country nationality = Country.valueOf(rsS.getString(6));
            ResultSet rsU = st3.executeQuery(String.format("SELECT * FROM USERS WHERE ID = %s",UserId));
            rsU.next();
            String username = rsU.getString(2);
            String password = rsU.getString(3);
            Movie m = new Movie(Id, Name, new Coordinates(CoordsId, X,Y), LocalDateTime.parse(CreationDate, dtformatter2), OscarsCount, GoldenPalmCount, UsaBoxOffice, mpaaRating, new Person(PersonId, Scname, birthday, height, passportID, nationality), new User(username,password));
            Movies.add(m);
        }
        return Movies;
    }
    public void initialize() throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("SET search_path TO prog;");
        st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS COORDINATES  (
                    ID SERIAL PRIMARY KEY,
                    X DOUBLE PRECISION NOT NULL,
                    Y REAL NOT NULL
                );""");
        st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS PERSON  (
                ID SERIAL PRIMARY KEY,
                NAME VARCHAR(255) NOT NULL,
                BIRTHDAY DATE,
                HEIGHT DOUBLE PRECISION,
                PASSPORT_ID VARCHAR(50),
                NATIONALITY VARCHAR(50)
        );""");
        st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS USERS (
                    ID SERIAL PRIMARY KEY,
                    USERNAME VARCHAR(255) NOT NULL,
                    PASSWORD VARCHAR(255) NOT NULL);
        """);
        st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS MOVIES (
                ID SERIAL PRIMARY KEY,
                NAME VARCHAR(255) NOT NULL,
                COORDINATES_ID INTEGER NOT NULL REFERENCES COORDINATES(ID),
                CREATION_DATE TIMESTAMP NOT NULL ,
                OSCARS_COUNT INTEGER,
                GOLDEN_PALM_COUNT BIGINT,
                USA_BOX_OFFICE INTEGER,
                MPAA_RATING VARCHAR(20),
                PERSON_ID INTEGER NOT NULL REFERENCES PERSON(ID),
                USER_ID INTEGER NOT NULL REFERENCES USERS(ID)
        );""");
    }
    public void addMovie(Movie m, Person p, Coordinates c, User u) throws SQLException {
        PreparedStatement st2;

        st2 = con.prepareStatement(DBQuery.CreateQuery("add_person", p));
        st2.setString(1,p.getName());
        st2.setObject(2,p.getBirthday());
        st2.setDouble(3,p.getHeight());
        st2.setString(4,p.getPassportID());
        st2.setString(5,p.getNationality().toString());
        st2.executeUpdate();
        st2 = con.prepareStatement(DBQuery.CreateQuery("add_coords", c));
        st2.setDouble(1,c.getX());
        st2.setFloat(2,c.getY());
        st2.executeUpdate();
        st2 = con.prepareStatement("SELECT ID FROM PERSON WHERE NAME = ? AND PASSPORT_ID = ?");
        st2.setString(1,p.getName());
        st2.setString(2,p.getPassportID());
        ResultSet set = st2.executeQuery();
        set.next();
        int PersonId = set.getInt(1);
        st2 = con.prepareStatement("SELECT ID FROM COORDINATES WHERE X = ? AND Y = ?");
        st2.setDouble(1,c.getX());
        st2.setFloat(2,c.getY());
        set = st2.executeQuery();
        set.next();
        int CoordsId = set.getInt(1);
        PreparedStatement st3 = con.prepareStatement("SELECT ID FROM USERS WHERE USERNAME = ?");
        st3.setString(1,u.getUsername());
        ResultSet set2 = st3.executeQuery();
        set2.next();
        int UserId = set2.getInt(1);
        m.setPersonId(PersonId);
        m.setCoordsId(CoordsId);
        m.setUserId(UserId);
        st2 = con.prepareStatement(DBQuery.CreateQuery("add_movie",m));
        st2.setString(1,m.getName());
        st2.setInt(2,CoordsId);
        st2.setObject(3,m.getCreationDate());
        st2.setInt(4,m.getOscarsCount());
        st2.setLong(5,m.getGoldenPalmCount());
        st2.setInt(6,m.getUsaBoxOffice());
        st2.setString(7,m.getMpaaRating().toString());
        st2.setInt(8,PersonId);
        st2.setInt(9,UserId);
        st2.executeUpdate();
    }
    public void addMovie(Movie m, Person p, Coordinates c, int id, User u) throws SQLException {
        PreparedStatement st2 ;
        m.setId(id);

        st2 = con.prepareStatement(DBQuery.CreateQuery("add_person", p));
        st2.setString(1,p.getName());
        st2.setObject(2,p.getBirthday());
        st2.setDouble(3,p.getHeight());
        st2.setString(4,p.getPassportID());
        st2.setString(5,p.getNationality().toString());
        st2.executeUpdate();
        st2 = con.prepareStatement(DBQuery.CreateQuery("add_coords", c));
        st2.setDouble(1,c.getX());
        st2.setFloat(2,c.getY());
        st2.executeUpdate();
        st2 = con.prepareStatement("SELECT ID FROM PERSON WHERE NAME = ? AND PASSPORT_ID = ?");
        st2.setString(1,p.getName());
        st2.setString(2,p.getPassportID());
        ResultSet set = st2.executeQuery();
        set.next();
        int PersonId = set.getInt(1);
        st2 = con.prepareStatement("SELECT ID FROM COORDINATES WHERE X = ? AND Y = ?");
        st2.setDouble(1,c.getX());
        st2.setFloat(2,c.getY());
        set = st2.executeQuery();
        set.next();
        int CoordsId = set.getInt(1);
        PreparedStatement st3 = con.prepareStatement("SELECT ID FROM USERS WHERE USERNAME = ?");
        st3.setString(1,u.getUsername());
        ResultSet set2 = st3.executeQuery();
        set2.next();
        int UserId = set2.getInt(1);
        m.setPersonId(PersonId);
        m.setCoordsId(CoordsId);
        m.setUserId(UserId);
        st2 = con.prepareStatement("DELETE FROM COORDINATES WHERE ID = ?");
        st2.setInt(1, CoordsId);
        st2 = con.prepareStatement("DELETE FROM PERSON WHERE ID = ?");
        st2.setInt(1, PersonId);
        st2 = con.prepareStatement("DELETE FROM MOVIES WHERE ID = ?");
        st2.setInt(1, id);

        st2 = con.prepareStatement(DBQuery.CreateQuery("add_movie_id",m));
        st2.setInt(1,m.getId());
        st2.setString(2,m.getName());
        st2.setInt(3,CoordsId);
        st2.setObject(4,m.getCreationDate());
        st2.setInt(5,m.getOscarsCount());
        st2.setLong(6,m.getGoldenPalmCount());
        st2.setInt(7,m.getUsaBoxOffice());
        st2.setString(8,m.getMpaaRating().toString());
        st2.setInt(9,PersonId);
        st2.setInt(10,UserId);
        st2.executeUpdate();
    }
    public boolean removeId(int id) throws SQLException {
        Statement st = con.createStatement();
        int changed = st.executeUpdate(String.format("DELETE FROM MOVIES WHERE ID = %s",id));
        return (changed>0);
    }
    public boolean addUser(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        Statement st = con.createStatement();
        PreparedStatement rq = con.prepareStatement("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?");
        rq.setString(1,username);
        ResultSet rs = rq.executeQuery();
        if(rs.next()){
            return false;
        }
        st.executeUpdate(String.format("INSERT INTO USERS(USERNAME, PASSWORD) VALUES ('%s', '%s')",username,password));
        return true;
    }

    public boolean loginUser(User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        PreparedStatement rq = con.prepareStatement(String.format("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?",username));
        rq.setString(1,username);
        ResultSet rs = rq.executeQuery();
        if (rs.next()){
            return rs.getString(2).equals(password);
        }
        return false;
    }
    public void clear() throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE COORDINATES CASCADE");
        st.executeUpdate("TRUNCATE TABLE PERSON CASCADE");
        st.executeUpdate("TRUNCATE TABLE MOVIES CASCADE");
    }
    public boolean findId(Movie m) throws SQLException {
        PreparedStatement rq = con.prepareStatement("SELECT * FROM MOVIES WHERE ID  = ?");
        rq.setInt(1, (m.getId()));
        ResultSet rs = rq.executeQuery();
        return rs.next();
    }
    public boolean matchesId(int id, String user) throws SQLException {
        PreparedStatement rq = con.prepareStatement("SELECT * FROM MOVIES JOIN USERS ON MOVIES.USER_ID = USERS.ID WHERE MOVIES.ID = ? AND USERNAME= ?");
        rq.setInt(1, id);
        rq.setString(2, user);
        ResultSet rs = rq.executeQuery();
        return rs.next();
    }
}