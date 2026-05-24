package Swing;

import Adapters.LocalDateAdapter;
import Adapters.LocalDateTimeAdapter;
import BaseFiles.Movie;
import BaseFiles.MpaaRating;import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class MovieTableModel extends AbstractTableModel {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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
            case 7 -> m.getUser().getUsername();
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
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1 -> String.class;
            case 2 -> LocalDate.class;
            case 3 -> Integer.class;
            case 4 -> Integer.class;
            case 5 -> Integer.class;
            case 6 -> MpaaRating.class;
            case 7 -> String.class;
            default -> Object.class;
        };
    }

}