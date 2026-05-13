package ui;

import BaseFiles.Movie;
import BaseFiles.MpaaRating;
import Service.MovieController;

import javax.swing.*;
import java.awt.*;

public class FilmRedactor extends JPanel {
    private JTextField nameField;
    private JTextField boxOfficeField;
    private JTextField oscarsField;

    private JComboBox<MpaaRating> ratingBox;
    private Movie currentMovie;
    private MovieController controller;
    public FilmRedactor(MovieController controller) {

        this.controller = controller;

        setLayout(new GridLayout(5, 2));

        nameField = new JTextField();
        boxOfficeField = new JTextField();
        oscarsField = new JTextField();
        ratingBox = new JComboBox<>(MpaaRating.values());


        JButton save = new JButton("Save");

        save.addActionListener(e -> saveMovie());

        add(new JLabel("Name"));
        add(nameField);

        add(new JLabel("Box Office"));
        add(boxOfficeField);

        add(new JLabel("Oscars"));
        add(oscarsField);

        add(new JLabel("Rating"));
        add(ratingBox);

        add(save);
    }
    public void setMovie(Movie movie) {

        this.currentMovie = movie;

        nameField.setText(movie.getName());

        boxOfficeField.setText(
                String.valueOf(movie.getUsaBoxOffice())
        );

        oscarsField.setText(
                String.valueOf(movie.getOscarsCount())
        );

        ratingBox.setSelectedItem(movie.getMpaaRating());
    }
    private void saveMovie() {

        currentMovie.setName(nameField.getText());

        currentMovie.setUsaBoxOffice(
                Integer.parseInt(boxOfficeField.getText())
        );

        currentMovie.setOscarsCount(
                Integer.parseInt(oscarsField.getText())
        );

        currentMovie.setMpaaRating(
                (MpaaRating) ratingBox.getSelectedItem()
        );

        controller.updateMovie(currentMovie);
    }
}
