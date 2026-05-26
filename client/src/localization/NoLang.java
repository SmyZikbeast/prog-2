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
    @Override public String save() { return "Lagre"; }
    @Override public String delete() { return "Slett"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("dd.MM.yyyy");}
}