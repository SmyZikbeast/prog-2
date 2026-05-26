package localization;

import java.time.format.DateTimeFormatter;

public class SeLang implements Lang {

    public String login() { return "Logga in"; }

    public String register() { return "Registrera"; }

    public String username() { return "Användarnamn"; }

    public String password() { return "Lösenord"; }

    public String list() { return "Lista"; }

    public String view() { return "Visa"; }

    public String editor() { return "Redigerare"; }

    public String id() { return "ID"; }

    public String name() { return "Namn"; }

    public String creationDate() { return "Skapelsedatum"; }

    public String oscarsCount() { return "Antal Oscar"; }

    public String goldenPalmCount() { return "Antal Guldpalmer"; }

    public String usaBoxOffice() { return "USA Box Office"; }

    public String MPAARating() { return "MPAA-betyg"; }

    public String user() { return "Användare"; }

    public String coordinates() { return "Koordinater"; }

    public String personName() { return "Namn"; }

    public String birthday() { return "Födelsedatum"; }

    public String height() { return "Längd"; }

    public String passportId() { return "Pass-ID"; }

    public String nationality() { return "Nationalitet"; }
    public String save() { return "Spara"; }
    public String delete() { return "Ta bort"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("yyyy-MM-dd");}
    public String help() { return "Hjälp"; }
    public String info() { return "Info"; }
    public String show() { return "Visa"; }
    public String add() { return "Lägg till"; }
    public String update() { return "Uppdatera"; }
    public String removeId() { return "Ta bort efter ID"; }
    public String executeScript() { return "Kör skript"; }
    public String history() { return "Historik"; }
    public String removeByUSABoxOffice() { return "Ta bort efter USA-intäkter"; }
    public String addIfMax() { return "Lägg till om max"; }
    public String addIfMin() { return "Lägg till om min"; }
    public String commands() {return "Kommandon";}
    public String execute() {return "Utföra";}
    public String console() {return "Trösta";}
}