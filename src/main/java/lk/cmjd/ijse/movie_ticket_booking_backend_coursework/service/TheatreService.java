package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import java.time.LocalDate;
import java.util.List;

public interface TheatreService {
    TheatreDTO createTheatre(TheatreDTO dto);
    TheatreDTO getSelectedTheatre(String id);
    List<TheatreDTO> getTheatres();
    TheatreDTO updateTheatre(String id, TheatreDTO dto);
    void deleteTheatre(String id);
}
