package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.CurrentUser;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MovieServiceIMPL implements MovieService {
    private final MovieDao movieDao;
    private final ShowDao showDao;
    private final Conversion conversion;

    @Override
    public MovieDTO createMovie(MovieDTO dto) {
        MovieEntity movie = conversion.toMovieEntity(dto);
        movie.setMovieId(IDGenerate.movieId());
        log.info("Created movie {}", movie.getMovieId());
        return conversion.toMovieDTO(movieDao.save(movie));
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDTO getSelectedMovie(String id) { return conversion.toMovieDTO(findMovie(id)); }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> getMovies(String title, String language, movieStatus status) {
        return movieDao.findAll().stream()
                .filter(movie -> title == null || movie.getTitle().toLowerCase(Locale.ROOT).contains(title.toLowerCase(Locale.ROOT)))
                .filter(movie -> language == null || movie.getLanguage().equalsIgnoreCase(language))
                .filter(movie -> status == null || movie.getStatus() == status)
                .map(conversion::toMovieDTO).toList();
    }

    @Override
    public MovieDTO updateMovie(String id, MovieDTO dto) {
        MovieEntity movie = findMovie(id);
        boolean scheduledShows = showDao.findByMovieMovieId(id).stream().anyMatch(show -> show.getStatus() == showStatus.SCHEDULED);
        if (scheduledShows && movie.getDuration() != dto.getDuration()) {
            throw new BusinessException("Cancel scheduled shows before changing the movie duration");
        }
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setDuration(dto.getDuration());
        movie.setLanguage(dto.getLanguage());
        movie.setGenre(dto.getGenre());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setStatus(dto.getStatus());
        log.info("Updated movie {}", id);
        return conversion.toMovieDTO(movieDao.save(movie));
    }

    @Override
    public void deleteMovie(String id) {
        MovieEntity movie = findMovie(id);
        if (showDao.existsByMovieMovieId(id)) { throw new BusinessException("A movie with shows cannot be deleted"); }
        movieDao.delete(movie);
        log.info("Deleted movie {}", id);
    }

    private MovieEntity findMovie(String id) {
        return movieDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }
}
