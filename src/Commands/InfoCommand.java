package Commands;

import Manager.CollectionManager;

public class InfoCommand extends Command{
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public void execute(){
        System.out.println("Тип коллекции:" + cm.getType());
        System.out.println("Дата инициализации:" + cm.getInitDate());
        System.out.println("Количество элементов:" + cm.getElementsAmount());
    }
}
