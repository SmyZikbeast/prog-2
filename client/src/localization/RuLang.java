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
    @Override public String save() { return "Сохранить"; }
    @Override public String delete() { return "Удалить"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("dd.MM.yyyy");}
}