package Commands;

import Manager.CollectionManager;
import Response.Response;
import postgres.DBInteractor;

import java.sql.SQLException;

public class RegisterCommand extends Command{
    public RegisterCommand(CollectionManager cm){super(cm);};
    @Override
    public Response execute() throws SQLException {
        DBInteractor interactor = cm.getInteractor();
        boolean f = interactor.addUser(this.user);
        return new Response("Boolean", f);
    }
}
