package localization;

import java.time.format.DateTimeFormatter;

public class NoLang implements Lang {

    public String login() { return "Logg inn"; }

    public String register() { return "Registrer"; }

    public String username() { return "Brukernavn"; }

    public String password() { return "Passord"; }

    public String list() { return "Liste"; }

    public String view() { return "Visning"; }

    public String editor() { return "Redigering"; }

    public String id() { return "ID"; }

    public String name() { return "Navn"; }

    public String creationDate() { return "Opprettelsesdato"; }

    public String oscarsCount() { return "Antall Oscar"; }

    public String goldenPalmCount() { return "Antall Gullpalmer"; }

    public String usaBoxOffice() { return "USA Box Office"; }

    public String MPAARating() { return "MPAA-vurdering"; }

    public String user() { return "Bruker"; }

    public String coordinates() { return "Koordinater"; }

    public String personName() { return "Navn"; }

    public String birthday() { return "Fødselsdato"; }

    public String height() { return "Høyde"; }

    public String passportId() { return "Pass-ID"; }

    public String nationality() { return "Nasjonalitet"; }
    public String save() { return "Lagre"; }
    public String delete() { return "Slett"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("dd.MM.yyyy");}
    public String help() { return "Hjelp"; }
    public String info() { return "Info"; }
    public String show() { return "Vis"; }
    public String add() { return "Legg til"; }
    public String update() { return "Oppdater"; }
    public String removeId() { return "Fjern etter ID"; }
    public String executeScript() { return "Kjør skript"; }
    public String history() { return "Historikk"; }
    public String removeByUSABoxOffice() { return "Fjern etter USA-inntekter"; }
    public String addIfMax() { return "Legg til hvis maks"; }
    public String addIfMin() { return "Legg til hvis min"; }
    public String commands() {return "Kommandoer";}
    public String execute() {return "Henrette";}
    public String console() {return "Konsoll";}
}