package ui;


import BaseFiles.Coordinates;
import BaseFiles.Movie;
import Service.ClientService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilmView extends JPanel {
    ClientService service;
    List<Movie> movieList;
    public FilmView(ClientService cs){
        this.service = cs;
        movieList = service.getTableModel().getMovies();
    }
    public static double map(
            double value,
            double inMin,
            double inMax,
            double outMin,
            double outMax
    ) {
        if (inMax == inMin) return outMin;

        return (value - inMin) *
                (outMax - outMin) /
                (inMax - inMin) +
                outMin;
    }
    public static Integer mapInt(
            Integer value,
            Integer inMin,
            Integer inMax,
            Integer outMin,
            Integer outMax
    ) {
        return (value - inMin) *
                (outMax - outMin) /
                (inMax - inMin) +
                outMin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        movieList = service.getTableModel().getMovies();
        if (!movieList.isEmpty()){
            Double maxX = Math.abs(movieList.stream().map(Movie::getCoordinates).map(Coordinates::getX).max(Double::compareTo).orElse(null));
            Double maxY = Math.abs((double)movieList.stream().map(Movie::getCoordinates).map(Coordinates::getY).max(Float::compareTo).orElse(1.0F));
            Integer maxOscar = movieList.stream()
                    .map(Movie::getOscarsCount)
                    .max(Integer::compareTo)
                    .orElse(1);

            Integer minOscar = movieList.stream()
                    .map(Movie::getOscarsCount)
                    .min(Integer::compareTo)
                    .orElse(1);
            for(Movie m: movieList) {
                Integer id = m.getId();
                Double x = m.getCoordinates().getX();
                Double y = (double)m.getCoordinates().getY();
                Integer oscar = m.getOscarsCount();
                Integer mappedX = (int)FilmView.map(x, 0, maxX, 0, this.getWidth()/2 - 50);
                Integer mappedY = (int)FilmView.map(y, 0, maxY, 0, this.getHeight()/2 - 50);
                Integer mappedOscar = (int)FilmView.map(oscar, minOscar, maxOscar, 100, 2000);
                System.out.println("x: "+mappedX);
                System.out.println("y: "+mappedY);
                System.out.println("oscar: "+Math.sqrt(mappedOscar));
                g.drawOval(this.getWidth()/2+mappedX-(int)Math.sqrt(mappedOscar)/2,this.getHeight()/2+mappedY-(int)Math.sqrt(mappedOscar)/2, (int)Math.sqrt(mappedOscar), (int)Math.sqrt(mappedOscar));
            }
        }
    }
}
