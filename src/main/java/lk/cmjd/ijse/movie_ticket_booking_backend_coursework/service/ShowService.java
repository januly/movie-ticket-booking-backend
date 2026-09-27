package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import java.time.LocalDate;
import java.util.List;

public interface ShowService {
    ShowDTO createShow(ShowDTO dto);
    ShowDTO getSelectedShow(String id);
    List<ShowDTO> getShows(String movieId, String theatreId, LocalDate date, showStatus status);
    List<ShowDTO> getShowsForMovie(String movieId);
    ShowDTO updateShow(String id, ShowDTO dto);
    void deleteShow(String id);
    SeatAvailabilityDTO getSeats(String id);
}
