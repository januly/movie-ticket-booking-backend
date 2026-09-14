package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.ShowDTO;

import java.util.List;

public interface ShowService {
    void createShow(ShowDTO show);
    ShowDTO getselectedShow(String showId);
    List<ShowDTO> getShows();
    void updateShow(String showId,ShowDTO show);
    void deleteShow(String showId);
}
