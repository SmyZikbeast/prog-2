package localization;

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
    @Override public String save() { return "Spara"; }
    @Override public String delete() { return "Ta bort"; }
}