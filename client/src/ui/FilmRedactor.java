package ui;

import BaseFiles.*;
import Service.ClientService;
import Service.MovieController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static BaseFiles.MpaaRating.G;


public class FilmRedactor extends JPanel {
    private JTextField idField;
    private JTextField nameField;
    private JTextField coordinatesXfield;
    private JTextField coordinatesYfield;
    private JTextField oscarsField;
    private JTextField boxOfficeField;
    private JTextField usaBoxOfficeField;
    private JComboBox<MpaaRating> ratingBox;
    private JTextField swNameField;
    private JTextField swBdayField;
    private JTextField swHeightField;
    private JTextField swPassportField;
    private JComboBox<Country> swNationalityField;
    private Movie currentMovie = new Movie(0,"0",new Coordinates(0.0,0),0,0L,0,G,new Person(0,"0",LocalDateTime.now(),0.0,"0", Country.INDIA),"0");
    private ClientService service;
    public FilmRedactor(ClientService service) {

        this.service = service;

        setLayout(new GridLayout(15, 2));

        idField = new JTextField();
        nameField = new JTextField();
        coordinatesXfield = new JTextField();
        coordinatesYfield = new JTextField();
        oscarsField = new JTextField();
        boxOfficeField = new JTextField();
        usaBoxOfficeField = new JTextField();
        ratingBox = new JComboBox<>(MpaaRating.values());
        swNameField = new JTextField();
        swBdayField = new JTextField();
        swHeightField = new JTextField();
        swPassportField = new JTextField();
        swNationalityField = new JComboBox<>(Country.values());


        JButton save = new JButton("Save");
        JButton delete = new JButton("Delete");
        save.addActionListener(e -> saveMovie());
        delete.addActionListener(e -> {
            try {
                deleteMovie(Integer.parseInt(idField.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(new JLabel("ID"));
        add(idField);

        add(new JLabel("Name"));
        add(nameField);

        add(new JLabel("Coordinates X"));
        add(coordinatesXfield);

        add(new JLabel("Coordinates Y"));
        add(coordinatesYfield);

        add(new JLabel("Oscars"));
        add(oscarsField);

        add(new JLabel("Box Office"));
        add(boxOfficeField);

        add(new JLabel("USA Box Office"));
        add(usaBoxOfficeField);

        add(new JLabel("Rating"));
        add(ratingBox);

// Screenwriter section

        add(new JLabel("Screenwriter Name"));
        add(swNameField);

        add(new JLabel("Screenwriter Birthday"));
        add(swBdayField);

        add(new JLabel("Screenwriter Height"));
        add(swHeightField);

        add(new JLabel("Screenwriter Passport"));
        add(swPassportField);

        add(new JLabel("Screenwriter Nationality"));
        add(swNationalityField);

        add(save);
        add(delete);
    }

    private void deleteMovie(int id) throws IOException {
        service.deleteMovie(id);
    }

    public void setMovie(Movie movie) {

        this.currentMovie = movie;

        // Movie fields
        idField.setText(String.valueOf(movie.getId()));
        nameField.setText(movie.getName());

        coordinatesXfield.setText(
                String.valueOf(movie.getCoordinates().getX())
        );

        coordinatesYfield.setText(
                String.valueOf(movie.getCoordinates().getY())
        );

        oscarsField.setText(
                String.valueOf(movie.getOscarsCount())
        );

        boxOfficeField.setText(
                String.valueOf(movie.getGoldenPalmCount())
        );

        usaBoxOfficeField.setText(
                String.valueOf(movie.getUsaBoxOffice())
        );

        ratingBox.setSelectedItem(movie.getMpaaRating());

        // Screenwriter fields
        swNameField.setText(movie.getScreenwriter().getName());

        swBdayField.setText(
                movie.getScreenwriter().getBirthday().toString()
        );

        swHeightField.setText(
                String.valueOf(movie.getScreenwriter().getHeight())
        );

        swPassportField.setText(movie.getScreenwriter().getPassportID());

        swNationalityField.setSelectedItem(
                movie.getScreenwriter().getNationality()
        );
    }
    private void saveMovie() {
        currentMovie.setId(Integer.parseInt(idField.getText()));
        currentMovie.setName(nameField.getText());

        currentMovie.setUsaBoxOffice(
                Integer.parseInt(usaBoxOfficeField.getText())
        );

        currentMovie.setOscarsCount(
                Integer.parseInt(oscarsField.getText())
        );

        currentMovie.setMpaaRating(
                (MpaaRating) ratingBox.getSelectedItem()
        );

        // Coordinates
        currentMovie.getCoordinates().setX(
                Double.parseDouble(coordinatesXfield.getText())
        );

        currentMovie.getCoordinates().setY(
                Float.parseFloat(coordinatesYfield.getText())
        );

        // Screenwriter
        currentMovie.getScreenwriter().setName(
                swNameField.getText()
        );
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm");
        String bday = swBdayField.getText();
        if (!bday.endsWith("T00:00")){
            bday = bday +"T00:00";
            currentMovie.getScreenwriter().setBirthday(
                    LocalDateTime.parse(bday,formatter)
            );
        }
        else {
            currentMovie.getScreenwriter().setBirthday(
                    LocalDateTime.parse(bday)
            );
        }

        currentMovie.getScreenwriter().setHeight(
                Double.parseDouble(swHeightField.getText())
        );

        currentMovie.getScreenwriter().setPassportID(
                swPassportField.getText()
        );

        currentMovie.getScreenwriter().setNationality(
                BaseFiles.Country.valueOf(swNationalityField.getSelectedItem().toString())
        );

        service.updateMovie(currentMovie);
    }
}
