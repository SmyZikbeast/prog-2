package Commands;

import Manager.CollectionManager;
import Manager.CommandManager;
import Response.Response;

public class HelpCommand extends Command{
    public HelpCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public Response execute(){
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "history : вывести последние 9 команд (без их аргументов)\n" +
                "remove_any_by_usa_box_office usaBoxOffice : удалить из коллекции один элемент, значение поля usaBoxOffice которого эквивалентно заданному\n" +
                "count_less_than_screenwriter screenwriter : вывести количество элементов, значение поля screenwriter которых меньше заданного\n" +
                "print_field_ascending_mpaa_rating : вывести значения поля mpaaRating всех элементов в порядке возрастания\n");
        CommandManager.addCommand("Help");
        return null;
    }
}
