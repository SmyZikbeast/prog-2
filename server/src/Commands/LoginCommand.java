package Commands;

import Manager.CollectionManager;
import Response.Response;
import postgres.DBInteractor;

import java.sql.SQLException;

public class LoginCommand extends Command{
    public LoginCommand(CollectionManager cm){super(cm);}
    @Override
    public Response execute() throws SQLException {
        DBInteractor interactor = cm.getInteractor();
        boolean f = interactor.loginUser(this.user);
        return new Response("Boolean", f);
    }
}
