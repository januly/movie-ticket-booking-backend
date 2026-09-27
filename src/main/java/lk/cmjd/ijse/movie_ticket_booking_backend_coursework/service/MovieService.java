package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    MovieDTO createMovie(MovieDTO dto);
    MovieDTO getSelectedMovie(String id);
    List<MovieDTO> getMovies(String title, String language, movieStatus status);
    MovieDTO updateMovie(String id, MovieDTO dto);
    void deleteMovie(String id);
}
