package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.MovieDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.TheatreDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.theatreStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/theatre")
@RestController
@RequiredArgsConstructor

public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTheatre(@RequestBody TheatreDTO theatreDTO) {
        theatreService.createTheatre(theatreDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{theatreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDTO> getselectedTheatre(@PathVariable String theatreId) {

        return new ResponseEntity<>(theatreService.getselectedTheatre(theatreId), HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TheatreDTO>> getTheaters() {

        return new ResponseEntity<>(theatreService.getTheatres(), HttpStatus.OK);
    }
    @PutMapping(value = "/{theatreId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDTO> updateTheatre(@PathVariable String theatreId, @RequestBody TheatreDTO theatreDTO) {
        theatreService.updateTheatre(theatreId,theatreDTO);
        return new ResponseEntity<>( HttpStatus.OK);

    }
    @DeleteMapping(value = "{theatreId}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable String theatreId) {
        theatreService.deleteTheatre(theatreId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
