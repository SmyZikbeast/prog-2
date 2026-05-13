package ui;

import BaseFiles.Movie;

import javax.swing.*;
import java.awt.*;

public class FilmView extends JPanel {

    private Movie movie;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel coordinatesLabel;
    private JLabel oscarsLabel;
    private JLabel boxOfficeLabel;
    private JLabel usaBoxOfficeLabel;
    private JLabel ratingLabel;

    private JLabel swNameLabel;
    private JLabel swBdayLabel;
    private JLabel swHeightLabel;
    private JLabel swPassportLabel;
    private JLabel swNationalityLabel;

    public FilmView() {

        idLabel = new JLabel();
        nameLabel = new JLabel();
        coordinatesLabel = new JLabel();
        oscarsLabel = new JLabel();
        boxOfficeLabel = new JLabel();
        usaBoxOfficeLabel = new JLabel();
        ratingLabel = new JLabel();

        swNameLabel = new JLabel();
        swBdayLabel = new JLabel();
        swHeightLabel = new JLabel();
        swPassportLabel = new JLabel();
        swNationalityLabel = new JLabel();

        setLayout(new GridLayout(1, 3, 10, 0));

        JPanel moviePanel = new JPanel(new GridLayout(0, 2));

        moviePanel.add(new JLabel("ID"));
        moviePanel.add(idLabel);

        moviePanel.add(new JLabel("Name"));
        moviePanel.add(nameLabel);

        moviePanel.add(new JLabel("Oscars"));
        moviePanel.add(oscarsLabel);

        moviePanel.add(new JLabel("Box Office"));
        moviePanel.add(boxOfficeLabel);

        moviePanel.add(new JLabel("USA Box Office"));
        moviePanel.add(usaBoxOfficeLabel);

        moviePanel.add(new JLabel("Rating"));
        moviePanel.add(ratingLabel);

        JPanel coordPanel = new JPanel(new GridLayout(0, 2));

        coordPanel.add(new JLabel("Coordinates"));
        coordPanel.add(coordinatesLabel);

        JPanel writerPanel = new JPanel(new GridLayout(0, 2));

        writerPanel.add(new JLabel("Name"));
        writerPanel.add(swNameLabel);

        writerPanel.add(new JLabel("Birthday"));
        writerPanel.add(swBdayLabel);

        writerPanel.add(new JLabel("Height"));
        writerPanel.add(swHeightLabel);

        writerPanel.add(new JLabel("Passport ID"));
        writerPanel.add(swPassportLabel);

        writerPanel.add(new JLabel("Nationality"));
        writerPanel.add(swNationalityLabel);

        add(moviePanel);
        add(coordPanel);
        add(writerPanel);
    }

    public void setMovie(Movie movie) {

        this.movie = movie;

        idLabel.setText(String.valueOf(movie.getId()));
        nameLabel.setText(movie.getName());

        coordinatesLabel.setText(
                "x=" + movie.getCoordinates().getX() +
                        ", y=" + movie.getCoordinates().getY()
        );

        oscarsLabel.setText(
                movie.getOscarsCount() == null ? "" : String.valueOf(movie.getOscarsCount())
        );

        boxOfficeLabel.setText(String.valueOf(movie.getGoldenPalmCount()));
        usaBoxOfficeLabel.setText(String.valueOf(movie.getUsaBoxOffice()));
        ratingLabel.setText(String.valueOf(movie.getMpaaRating()));

        swNameLabel.setText(movie.getScreenwriter().getName());
        swBdayLabel.setText(movie.getScreenwriter().getBirthday().toString());
        swHeightLabel.setText(String.valueOf(movie.getScreenwriter().getHeight()));
        swPassportLabel.setText(movie.getScreenwriter().getPassportID());
        swNationalityLabel.setText(String.valueOf(movie.getScreenwriter().getNationality()));
    }
}