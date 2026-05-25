package ui;

import BaseFiles.Movie;

public class FilmCircle {
    int x;
    int y;
    int radius;
    Movie movie;
    public FilmCircle(Movie movie, int x, int y, int radius){
        this.movie = movie;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public boolean contains(int x, int y){
        return ((this.x < x) && (x < this.x+radius) && ((this.y < y) && (y < this.y + radius)));
    }
    public Movie getMovie() {
        return this.movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
