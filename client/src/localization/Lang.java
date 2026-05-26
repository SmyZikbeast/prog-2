package localization;

import java.time.format.DateTimeFormatter;

public interface Lang {
    String login();
    String register();
    String username();
    String password();
    String list();
    String view();
    String editor();
    String id();
    String name();
    String creationDate();
    String oscarsCount();
    String goldenPalmCount();
    String usaBoxOffice();
    String MPAARating();
    String user();
    String coordinates();
    String personName();
    String birthday();
    String height();
    String passportId();
    String nationality();
    String save();
    String delete();
    DateTimeFormatter formatter();
}
