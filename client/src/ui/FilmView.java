package ui;

import BaseFiles.Movie;
import localization.Localizable;
import localization.LocalizationManager;

import javax.swing.*;
import java.awt.*;

public class FilmView extends JPanel implements Localizable {

    private final LocalizationManager lm;

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

    private JLabel idText;
    private JLabel nameText;
    private JLabel coordinatesText;
    private JLabel oscarsText;
    private JLabel boxOfficeText;
    private JLabel usaBoxOfficeText;
    private JLabel ratingText;

    private JLabel swNameText;
    private JLabel swBdayText;
    private JLabel swHeightText;
    private JLabel swPassportText;
    private JLabel swNationalityText;

    public FilmView(LocalizationManager lm) {

        this.lm = lm;

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

        idText = new JLabel();
        nameText = new JLabel();
        coordinatesText = new JLabel();
        oscarsText = new JLabel();
        boxOfficeText = new JLabel();
        usaBoxOfficeText = new JLabel();
        ratingText = new JLabel();

        swNameText = new JLabel();
        swBdayText = new JLabel();
        swHeightText = new JLabel();
        swPassportText = new JLabel();
        swNationalityText = new JLabel();

        setLayout(new GridLayout(1, 3, 10, 0));

        JPanel moviePanel =
                new JPanel(new GridLayout(0, 2));

        moviePanel.add(idText);
        moviePanel.add(idLabel);

        moviePanel.add(nameText);
        moviePanel.add(nameLabel);

        moviePanel.add(oscarsText);
        moviePanel.add(oscarsLabel);

        moviePanel.add(boxOfficeText);
        moviePanel.add(boxOfficeLabel);

        moviePanel.add(usaBoxOfficeText);
        moviePanel.add(usaBoxOfficeLabel);

        moviePanel.add(ratingText);
        moviePanel.add(ratingLabel);

        JPanel coordPanel =
                new JPanel(new GridLayout(0, 2));

        coordPanel.add(coordinatesText);
        coordPanel.add(coordinatesLabel);

        JPanel writerPanel =
                new JPanel(new GridLayout(0, 2));

        writerPanel.add(swNameText);
        writerPanel.add(swNameLabel);

        writerPanel.add(swBdayText);
        writerPanel.add(swBdayLabel);

        writerPanel.add(swHeightText);
        writerPanel.add(swHeightLabel);

        writerPanel.add(swPassportText);
        writerPanel.add(swPassportLabel);

        writerPanel.add(swNationalityText);
        writerPanel.add(swNationalityLabel);

        add(moviePanel);
        add(coordPanel);
        add(writerPanel);

        updateLanguage();
    }

    @Override
    public void updateLanguage() {

        idText.setText(
                lm.getLang().id()
        );

        nameText.setText(
                lm.getLang().name()
        );

        coordinatesText.setText(
                lm.getLang().coordinates()
        );

        oscarsText.setText(
                lm.getLang().oscarsCount()
        );

        boxOfficeText.setText(
                lm.getLang().goldenPalmCount()
        );

        usaBoxOfficeText.setText(
                lm.getLang().usaBoxOffice()
        );

        ratingText.setText(
                lm.getLang().MPAARating()
        );

        swNameText.setText(
                lm.getLang().personName()
        );

        swBdayText.setText(
                lm.getLang().birthday()
        );

        swHeightText.setText(
                lm.getLang().height()
        );

        swPassportText.setText(
                lm.getLang().passportId()
        );

        swNationalityText.setText(
                lm.getLang().nationality()
        );

        repaint();
        revalidate();
    }

    public void setMovie(Movie movie) {

        this.movie = movie;

        idLabel.setText(
                String.valueOf(movie.getId())
        );

        nameLabel.setText(
                movie.getName()
        );

        coordinatesLabel.setText(
                "x=" + movie.getCoordinates().getX()
                        + ", y=" + movie.getCoordinates().getY()
        );

        oscarsLabel.setText(
                movie.getOscarsCount() == null
                        ? ""
                        : String.valueOf(movie.getOscarsCount())
        );

        boxOfficeLabel.setText(
                String.valueOf(movie.getGoldenPalmCount())
        );

        usaBoxOfficeLabel.setText(
                String.valueOf(movie.getUsaBoxOffice())
        );

        ratingLabel.setText(
                String.valueOf(movie.getMpaaRating())
        );

        swNameLabel.setText(
                movie.getScreenwriter().getName()
        );

        swBdayLabel.setText(
                String.valueOf(movie.getScreenwriter().getBirthday())
        );

        swHeightLabel.setText(
                String.valueOf(movie.getScreenwriter().getHeight())
        );

        swPassportLabel.setText(
                movie.getScreenwriter().getPassportID()
        );

        swNationalityLabel.setText(
                String.valueOf(movie.getScreenwriter().getNationality())
        );
    }
}