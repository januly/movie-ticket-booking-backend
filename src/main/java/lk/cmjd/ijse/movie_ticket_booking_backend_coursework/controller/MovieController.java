package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.movieStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.MovieService;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/movies")
@RestController
@RequiredArgsConstructor

public class MovieController {

    private final MovieService movieService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createMovie(@RequestBody MovieDTO movieDTO) {
        movieService.createMovie(movieDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> getselectedMovie(@PathVariable String movieId) {
        return new ResponseEntity<>(movieService.getselectedMovie(movieId), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }

    @PutMapping(value = "{movieId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable String movieId, @RequestBody MovieDTO movieDTO) {
        movieService.updateMovie(movieId,movieDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping(value = "{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
