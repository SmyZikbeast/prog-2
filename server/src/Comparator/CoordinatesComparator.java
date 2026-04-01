package Comparator;

import BaseFiles.Movie;

import java.util.Comparator;

public class CoordinatesComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getCoordinates() - o2.getCoordinates()<0){
            return -1;
        };
        if (Math.abs(o1.getCoordinates() - o2.getCoordinates())<0.00001){
            return 0;
        };
        return 1;
    }
}
