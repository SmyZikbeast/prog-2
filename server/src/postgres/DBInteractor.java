package postgres;

import BaseFiles.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;


public class DBInteractor {
    Connection con;
    public DBInteractor(Connection con) {
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
            String user = rs.getString("USERNAME");
            ResultSet rsC = st2.executeQuery(String.format("SELECT * FROM COORDINATES WHERE ID = %s;",CoordsId));
            rsC.next();
            double X = rsC.getDouble("X");
            float Y = rsC.getFloat("Y");
            ResultSet rsS = st3.executeQuery(String.format("SELECT * FROM PERSON WHERE ID = %s",PersonId));
            rsS.next();
            String Scname = rsS.getString(2);
            LocalDateTime birthday = DateTimeConverter.toDate(rsS.getString(3));
            Double height = rsS.getDouble(4);
            String passportID = rsS.getString(5);
            Country nationality = Country.valueOf(rsS.getString(6));
            Movie m = new Movie(Id, Name, new Coordinates(CoordsId, X,Y), DateTimeConverter.toDate(CreationDate), OscarsCount, GoldenPalmCount, UsaBoxOffice, mpaaRating, new Person(PersonId, Scname, birthday, height, passportID, nationality), user);
            Movies.add(m);
        }
        return Movies;
    }
    public void addMovie(Movie m, Person p, Coordinates c) throws SQLException {
        Statement st1 = con.createStatement();
        Statement st2 = con.createStatement();
        String q2 = DBQuery.CreateQuery("add_person", p);
        String q3 = DBQuery.CreateQuery("add_coords", c);
        ResultSet set = st1.executeQuery("SELECT LAST_VALUE FROM PERSON_ID_SEQ");
        set.next();
        int PersonId = set.getInt(1)+1;
        ResultSet set1 = st1.executeQuery("SELECT LAST_VALUE FROM COORDINATES_ID_SEQ");
        set1.next();
        int CoordsId = set1.getInt(1)+1;
        m.setPersonId(PersonId);
        System.out.println(CoordsId);
        m.setCoordsId(CoordsId);
        st2.executeUpdate(q2);
        st2.executeUpdate(q3);
        String q1 = DBQuery.CreateQuery("add_movie",m);
        st1.executeUpdate(q1);
    }
    public void addMovie(Movie m, Person p, Coordinates c, int id) throws SQLException {
        m.setId(id);
        Statement st1 = con.createStatement();
        Statement st2 = con.createStatement();
        String q2 = DBQuery.CreateQuery("add_person", p);
        String q3 = DBQuery.CreateQuery("add_coords", c);
        ResultSet set = st1.executeQuery("SELECT LAST_VALUE FROM PERSON_ID_SEQ");
        set.next();
        int PersonId = set.getInt(1)+1;
        ResultSet set1 = st1.executeQuery("SELECT LAST_VALUE FROM COORDINATES_ID_SEQ");
        set1.next();
        int CoordsId = set1.getInt(1)+1;
        m.setPersonId(PersonId);
        m.setCoordsId(CoordsId);
        st2.executeUpdate(q2);
        st2.executeUpdate(q3);
        String q1 = DBQuery.CreateQuery("add_movie_id",m);
        st1.executeUpdate(q1);
    }
    public boolean removeId(int id) throws SQLException {
        Statement st = con.createStatement();
        int changed = st.executeUpdate(String.format("DELETE FROM MOVIES WHERE ID = %s",id));
        return (changed>0);
    }
    public boolean addUser(String username, String password) throws SQLException {
        System.out.println(username);
        System.out.println(password);
        Statement st = con.createStatement();
        PreparedStatement rq = con.prepareStatement("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?");
        rq.setString(1,username);
        ResultSet rs = rq.executeQuery();
        System.out.println(rq);
        if(rs.next()){
            return false;
        }
        st.executeUpdate(String.format("INSERT INTO USERS(USERNAME, PASSWORD) VALUES ('%s', '%s')",username,password));
        return true;
    }

    public boolean loginUser(String username, String password) throws SQLException {
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
}