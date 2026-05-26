package localization;

import java.time.format.DateTimeFormatter;

public class RuLang implements Lang {

    public String login() { return "Вход"; }

    public String register() { return "Регистрация"; }

    public String username() { return "Имя пользователя"; }

    public String password() { return "Пароль"; }

    public String list() { return "Список"; }

    public String view() { return "Просмотр"; }

    public String editor() { return "Редактор"; }

    public String id() { return "ID"; }

    public String name() { return "Название"; }

    public String creationDate() { return "Дата создания"; }

    public String oscarsCount() { return "Количество Оскаров"; }

    public String goldenPalmCount() { return "Количество Золотых пальм"; }

    public String usaBoxOffice() { return "Сборы в США"; }

    public String MPAARating() { return "MPAA рейтинг"; }

    public String user() { return "Пользователь"; }

    public String coordinates() { return "Координаты"; }

    public String personName() { return "Имя"; }

    public String birthday() { return "Дата рождения"; }

    public String height() { return "Рост"; }

    public String passportId() { return "Паспорт"; }

    public String nationality() { return "Национальность"; }
    public String save() { return "Сохранить"; }
    public String delete() { return "Удалить"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("dd.MM.yyyy");}
    public String help() { return "Помощь"; }
    public String info() { return "Информация"; }
    public String show() { return "Показать"; }
    public String add() { return "Добавить"; }
    public String update() { return "Обновить"; }
    public String removeId() { return "Удалить по ID"; }
    public String executeScript() { return "Выполнить скрипт"; }
    public String history() { return "История"; }
    public String removeByUSABoxOffice() { return "Удалить по сборам США"; }
    public String addIfMax() { return "Добавить если максимум"; }
    public String addIfMin() { return "Добавить если минимум"; }
    public String commands() {return "Команды";}
    public String execute() {return "Выполнить";}
    public String console() {return "Консоль";}
}