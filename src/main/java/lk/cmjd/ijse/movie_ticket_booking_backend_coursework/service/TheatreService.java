package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.ShowDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.TheatreDTO;

import java.util.List;

public interface TheatreService {
    void createTheatre(TheatreDTO theatre);
    TheatreDTO getselectedTheatre(String theatreId);
    List<TheatreDTO> getTheatres();
    void updateTheatre(String theatreId,TheatreDTO theatre);
    void deleteTheatre(String theatreId);
}
