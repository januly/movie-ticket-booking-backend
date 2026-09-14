package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    void createMovie(MovieDTO movie);
    MovieDTO getselectedMovie(String movieId);
    List<MovieDTO> getMovies();
    void updateMovie(String movieId,MovieDTO movie);
    void deleteMovie(String movieid);
}
