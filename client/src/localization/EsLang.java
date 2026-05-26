package localization;

import java.time.format.DateTimeFormatter;

public class EsLang implements Lang {

    public String login() { return "Iniciar sesión"; }

    public String register() { return "Registrarse"; }

    public String username() { return "Nombre de usuario"; }

    public String password() { return "Contraseña"; }

    public String list() { return "Lista"; }

    public String view() { return "Vista"; }

    public String editor() { return "Editor"; }

    public String id() { return "ID"; }

    public String name() { return "Nombre"; }

    public String creationDate() { return "Fecha de creación"; }

    public String oscarsCount() { return "Cantidad de Óscar"; }

    public String goldenPalmCount() { return "Cantidad de Palmas de Oro"; }

    public String usaBoxOffice() { return "Taquilla en EE.UU."; }

    public String MPAARating() { return "Clasificación MPAA"; }

    public String user() { return "Usuario"; }

    public String coordinates() { return "Coordenadas"; }

    public String personName() { return "Nombre"; }

    public String birthday() { return "Fecha de nacimiento"; }

    public String height() { return "Altura"; }

    public String passportId() { return "Pasaporte"; }

    public String nationality() { return "Nacionalidad"; }
    public String save() { return "Guardar"; }
    public String delete() { return "Eliminar"; }
    public DateTimeFormatter formatter(){return DateTimeFormatter.ofPattern("dd/MM/yyyy");}
    public String help() { return "Ayuda"; }
    public String info() { return "Información"; }
    public String show() { return "Mostrar"; }
    public String add() { return "Añadir"; }
    public String update() { return "Actualizar"; }
    public String removeId() { return "Eliminar por ID"; }
    public String executeScript() { return "Ejecutar script"; }
    public String history() { return "Historial"; }
    public String removeByUSABoxOffice() { return "Eliminar por taquilla USA"; }
    public String addIfMax() { return "Añadir si máximo"; }
    public String addIfMin() { return "Añadir si mínimo"; }
    public String commands() {return "Comandos";}
    public String execute() {return "Ejecutar";}
    public String console() {return "Consola";}
}