package ui;

import BaseFiles.*;
import Service.ClientService;
import localization.Localizable;
import localization.LocalizationManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static BaseFiles.MpaaRating.G;

public class FilmRedactor extends JPanel implements Localizable {

    private final ClientService service;
    private final LocalizationManager lm;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel coordinatesXLabel;
    private JLabel coordinatesYLabel;
    private JLabel oscarsLabel;
    private JLabel boxOfficeLabel;
    private JLabel usaBoxOfficeLabel;
    private JLabel ratingLabel;

    private JLabel swNameLabel;
    private JLabel swBdayLabel;
    private JLabel swHeightLabel;
    private JLabel swPassportLabel;
    private JLabel swNationalityLabel;

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

    private Movie currentMovie =
            new Movie(0, "0",
                    new Coordinates(0.0, 0),
                    0, 0L, 0, G,
                    new Person(0, "0",
                            LocalDateTime.now(),
                            0.0,
                            "0",
                            Country.INDIA),
                    "0");

    public FilmRedactor(ClientService service, LocalizationManager lm) {

        this.service = service;
        this.lm = lm;

        setLayout(new GridLayout(15, 2));

        initComponents();
        buildUI();
    }

    private void initComponents() {

        idLabel = new JLabel();
        nameLabel = new JLabel();
        coordinatesXLabel = new JLabel();
        coordinatesYLabel = new JLabel();
        oscarsLabel = new JLabel();
        boxOfficeLabel = new JLabel();
        usaBoxOfficeLabel = new JLabel();
        ratingLabel = new JLabel();

        swNameLabel = new JLabel();
        swBdayLabel = new JLabel();
        swHeightLabel = new JLabel();
        swPassportLabel = new JLabel();
        swNationalityLabel = new JLabel();

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
    }

    private void buildUI() {

        JButton save = new JButton("Save");
        JButton delete = new JButton("Delete");

        save.addActionListener(e -> saveMovie());

        delete.addActionListener(e -> {
            try {
                service.deleteMovie(Integer.parseInt(idField.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        add(idLabel); add(idField);
        add(nameLabel); add(nameField);

        add(coordinatesXLabel); add(coordinatesXfield);
        add(coordinatesYLabel); add(coordinatesYfield);

        add(oscarsLabel); add(oscarsField);
        add(boxOfficeLabel); add(boxOfficeField);
        add(usaBoxOfficeLabel); add(usaBoxOfficeField);
        add(ratingLabel); add(ratingBox);

        add(swNameLabel); add(swNameField);
        add(swBdayLabel); add(swBdayField);
        add(swHeightLabel); add(swHeightField);
        add(swPassportLabel); add(swPassportField);
        add(swNationalityLabel); add(swNationalityField);

        add(save);
        add(delete);

        updateLanguage();
    }

    public void setMovie(Movie movie) {

        this.currentMovie = movie;

        idField.setText(String.valueOf(movie.getId()));
        nameField.setText(movie.getName());

        coordinatesXfield.setText(String.valueOf(movie.getCoordinates().getX()));
        coordinatesYfield.setText(String.valueOf(movie.getCoordinates().getY()));

        oscarsField.setText(String.valueOf(movie.getOscarsCount()));
        boxOfficeField.setText(String.valueOf(movie.getGoldenPalmCount()));
        usaBoxOfficeField.setText(String.valueOf(movie.getUsaBoxOffice()));

        ratingBox.setSelectedItem(movie.getMpaaRating());

        swNameField.setText(movie.getScreenwriter().getName());
        swBdayField.setText(movie.getScreenwriter().getBirthday().toString());
        swHeightField.setText(String.valueOf(movie.getScreenwriter().getHeight()));
        swPassportField.setText(movie.getScreenwriter().getPassportID());
        swNationalityField.setSelectedItem(movie.getScreenwriter().getNationality());
    }

    @Override
    public void updateLanguage() {

        idLabel.setText(lm.getLang().id());
        nameLabel.setText(lm.getLang().name());

        coordinatesXLabel.setText(lm.getLang().coordinates() + " X");
        coordinatesYLabel.setText(lm.getLang().coordinates() + " Y");

        oscarsLabel.setText(lm.getLang().oscarsCount());
        boxOfficeLabel.setText(lm.getLang().goldenPalmCount());
        usaBoxOfficeLabel.setText(lm.getLang().usaBoxOffice());
        ratingLabel.setText(lm.getLang().MPAARating());

        swNameLabel.setText(lm.getLang().personName());
        swBdayLabel.setText(lm.getLang().birthday());
        swHeightLabel.setText(lm.getLang().height());
        swPassportLabel.setText(lm.getLang().passportId());
        swNationalityLabel.setText(lm.getLang().nationality());

        revalidate();
        repaint();
    }

    private void saveMovie() {

        currentMovie.setId(Integer.parseInt(idField.getText()));
        currentMovie.setName(nameField.getText());

        currentMovie.setUsaBoxOffice(Integer.parseInt(usaBoxOfficeField.getText()));
        currentMovie.setOscarsCount(Integer.parseInt(oscarsField.getText()));
        currentMovie.setMpaaRating((MpaaRating) ratingBox.getSelectedItem());

        currentMovie.getCoordinates().setX(Double.parseDouble(coordinatesXfield.getText()));
        currentMovie.getCoordinates().setY(Float.parseFloat(coordinatesYfield.getText()));

        currentMovie.getScreenwriter().setName(swNameField.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm");

        String bday = swBdayField.getText();
        if (!bday.contains("T")) {
            bday = bday + "T00:00";
        }

        currentMovie.getScreenwriter().setBirthday(
                LocalDateTime.parse(bday, formatter)
        );

        currentMovie.getScreenwriter().setHeight(Double.parseDouble(swHeightField.getText()));
        currentMovie.getScreenwriter().setPassportID(swPassportField.getText());
        currentMovie.getScreenwriter().setNationality(
                Country.valueOf(swNationalityField.getSelectedItem().toString())
        );

        service.updateMovie(currentMovie);
    }
}