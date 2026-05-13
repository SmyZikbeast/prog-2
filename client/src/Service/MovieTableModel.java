package Service;

import Adapters.LocalDateTimeAdapter;
import Adapters.ZonedDateTimeAdapter;
import BaseFiles.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class MovieTableModel extends AbstractTableModel {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    private List<Movie> movies = new LinkedList<>();

    private final String[] cols = {"ID", "Name","Creation Date", "Oscars Count", "Golden Palm Count", "Usa Box Office", "MPAA rating",  "User"};
    @Override
    public Object getValueAt(int row, int col) {

        Movie m = gson.fromJson(
                gson.toJson(movies.get(row)),
                Movie.class
        );

        return switch (col) {
            case 0 -> m.getId();
            case 1 -> m.getName();
            case 2 -> m.getCreationDate();
            case 3 -> m.getOscarsCount();
            case 4 -> m.getGoldenPalmCount();
            case 5 -> m.getUsaBoxOffice();
            case 6 -> m.getMpaaRating();
            case 7 -> m.getUser();
            default -> "";
        };
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies.stream()
                .map(m -> gson.fromJson(
                        gson.toJson(m),
                        Movie.class
                ))
                .toList();
        fireTableDataChanged();
    }

    public Movie getMovieAt(int row) {
        return movies.get(row);
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }

}