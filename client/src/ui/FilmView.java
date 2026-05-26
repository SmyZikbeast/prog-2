package ui;


import BaseFiles.Coordinates;
import BaseFiles.Movie;
import Service.ClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FilmView extends JPanel {
    List<FilmCircle> circleList = new ArrayList<>();
    ClientService service;
    List<Movie> movieList;
    public FilmView(ClientService cs){
        this.service = cs;
        movieList = service.getTableModel().getMovies();
        service.getTableModel().addTableModelListener(e -> {
            repaint();
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();
                for (FilmCircle c : circleList) {
                    if (c.contains(mx, my)) {
                        service.getMainFrame().openEditor(c.getMovie());
                        break;
                    }
                }
            }
        });
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circleList.clear();
        movieList = service.getTableModel().getMovies();
        if (!movieList.isEmpty()){
            Double maxX = Math.abs(movieList.stream().map(Movie::getCoordinates).map(Coordinates::getX).map(Math::abs).max(Double::compareTo).orElse(null));
            Double maxY = Math.abs((double)movieList.stream().map(Movie::getCoordinates).map(Coordinates::getY).map(Math::abs).max(Float::compareTo).orElse(1.0F));
            Integer maxOscar = movieList.stream()
                    .map(Movie::getOscarsCount)
                    .max(Integer::compareTo)
                    .orElse(1);

            Integer minOscar = movieList.stream()
                    .map(Movie::getOscarsCount)
                    .min(Integer::compareTo)
                    .orElse(1);
            for(Movie m: movieList) {
                Double x = m.getCoordinates().getX();
                Double y = (double)m.getCoordinates().getY();
                Integer oscar = m.getOscarsCount();
                Integer mappedX = (int)FilmView.map(x, 0, maxX, 0, this.getWidth()/2 - 50);
                Integer mappedY = (int)FilmView.map(y, 0, maxY, 0, this.getHeight()/2 - 50);
                Integer mappedOscar = (int)FilmView.map(oscar, minOscar, maxOscar, this.getWidth()/2 - 50, 5 * this.getWidth());
                Color color = Color.getHSBColor((Math.abs(m.getUser().getUsername().hashCode())%360)/360f, 0.7f, 0.9f);
                g.setColor(color);
                g.fillOval(this.getWidth()/2+mappedX-(int)Math.sqrt(mappedOscar)/2,this.getHeight()/2+mappedY-(int)Math.sqrt(mappedOscar)/2, (int)Math.sqrt(mappedOscar), (int)Math.sqrt(mappedOscar));
                circleList.add(new FilmCircle(m,this.getWidth()/2+mappedX-(int)Math.sqrt(mappedOscar)/2,this.getHeight()/2+mappedY-(int)Math.sqrt(mappedOscar)/2,(int)Math.sqrt(mappedOscar)));
            }
        }
    }
}
