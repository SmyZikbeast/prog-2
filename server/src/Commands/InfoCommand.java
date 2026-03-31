package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;

public class InfoCommand extends Command{
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        System.out.println("Тип коллекции:" + cm.getType());
        System.out.println("Дата инициализации:" + cm.getInitDate());
        System.out.println("Количество элементов:" + cm.getElementsAmount());
        System.out.print("Автор: @SmyZikBest\n");
        CommandManager.addCommand("Info");
    }
}
