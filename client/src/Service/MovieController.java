package Service;

import BaseFiles.Movie;
import Response.*;
import ui.MainFrame;

public class MovieController {
    private ClientService service;
    private MainFrame frame;

    public MovieController(ClientService service, MainFrame frame) {
        this.service = service;
        this.frame = frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public void openMovieEditor(int row) {
        Movie movie = service.getTableModel().getMovieAt(row);
        frame.openEditor(movie);
    }

}
