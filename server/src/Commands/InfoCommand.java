package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;
/**
 * used to get info about collection
 *
 *
 *
 */
public class InfoCommand extends Command{
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public Response execute(){
        Response r = new Response("StringList",
                new String[] {"Тип коллекции:" + cm.getType(),
        "Дата инициализации:" + cm.getInitDate(),
        "Количество элементов:" + cm.getElementsAmount(),
        "Автор: @SmyZikBest\n"});
        CommandManager.addCommand("Info");
        return r;
    }
}
