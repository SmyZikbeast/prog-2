package Comparator;

import BaseFiles.Movie;

import java.util.Comparator;
/**
 * helping class to compare movies by their coordinates
 *
 *
 *
 */
public class CoordinatesComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        if (o1.getDistance() - o2.getDistance()<0){
            return -1;
        };
        if (Math.abs(o1.getDistance() - o2.getDistance())<0.00001){
            return 0;
        };
        return 1;
    }
}
