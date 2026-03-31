package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class InfoCommand extends Command{
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        Response r = new Response("String",
        "Тип коллекции:" + cm.getType() + "\n "+
        "Дата инициализации:" + cm.getInitDate() + "\n "+
        "Количество элементов:" + cm.getElementsAmount() + "\n "+
        "Автор: @SmyZikBest\n");
        CommandManager.addCommand("Info");
        return r;
    }
}
