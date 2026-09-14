package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;


import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.MovieDao;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.movieStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.MovieEntity;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.MovieService;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.Conversion;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.IDGenerate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceIMPL implements MovieService {

    private  final MovieDao movieDao;
    private final Conversion conversion;


    @Override
    public void createMovie(MovieDTO movie) {
        //Generate MovieId
        movie.setMovieId(IDGenerate.movieId());
        //saveData
        movieDao.save(conversion.toMovieEntity(movie));


    }

    @Override
    public MovieDTO getselectedMovie(String movieId) {
        return new MovieDTO(
                "M001",
                "Avengers: Doomsday",
                "The Avengers face a new threat involving Doctor Doom.",
                "TBA",
                "English",
                "Action/Sci-Fi",
                "2026-12-18",
                movieStatus.UPCOMING );
    }

    @Override
    public List<MovieDTO> getMovies() {
        List<MovieDTO> moviesList = List.of(
                new MovieDTO(
                        "M001",
                        "Avengers: Doomsday",
                        "The Avengers face a new threat involving Doctor Doom.",
                        "TBA",
                        "English",
                        "Action/Sci-Fi",
                        "2026-12-18",
                        movieStatus.UPCOMING
                ),
                new MovieDTO(
                        "M002",
                        "The Social Reckoning",
                        "A drama about a whistleblower exposing social media secrets.",
                        "112 minutes",
                        "English",
                        "Drama/Thriller",
                        "2026-10-09",
                        movieStatus.NOW_SHOWING
                ),
                new MovieDTO(
                        "M003",
                        "The Conjuring",
                        "Paranormal investigators help a family haunted by supernatural forces.",
                        "112 minutes",
                        "English",
                        "Horror",
                        "2013-07-19",
                        movieStatus.ENDED
                ),
                new MovieDTO(
                        "M004",
                        "Avengers: Secret Wars",
                        "Marvel superheroes return for a major multiverse adventure.",
                        "TBA",
                        "English",
                        "Action/Sci-Fi",
                        "2027-12-17",
                        movieStatus.UPCOMING
                )
        );
        return moviesList;
    }

    @Override
    public void updateMovie(String movieId, MovieDTO movie) {
        System.out.println("Updating Movie id is: " + movieId+ " and movie is: "+ movie.toString() );

    }

    @Override
    public void deleteMovie(String movieid) {
        System.out.println("Deleted Movie Id is: " + movieid);
    }
}
