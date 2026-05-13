package Commands;

import BaseFiles.Movie;
import BaseFiles.Person;
import Manager.CollectionManager;
import Response.Response;
import Utility.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * parent class for all commands
 *
 *
 *
 */
public abstract class Command {
    CollectionManager cm;
    Object arg = null;
    User user = null;

    Command(CollectionManager cm){
        this.cm = cm;
    }

    public Response execute() throws IOException, SQLException {return null;};

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public Response execute(String id) throws IOException {
        return null;
    };

    public void setUser(User commandUser) {
        this.user = commandUser;
    }
    @Override
    public String toString(){
        return "arg: "+arg + " user: "+user;
    }
}
