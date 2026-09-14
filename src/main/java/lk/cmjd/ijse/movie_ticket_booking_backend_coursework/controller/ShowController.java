package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.ShowDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.showStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/shows")
@RestController
@RequiredArgsConstructor

public class ShowController {
    private final ShowService showService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createShow(@RequestBody ShowDTO showDTO) {
        showService.createShow(showDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{showId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowDTO> getselectedShow(@PathVariable String showId) {
        return new ResponseEntity<> (showService.getselectedShow(showId),HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShowDTO>> getShows() {
        return new ResponseEntity<>(showService.getShows(), HttpStatus.OK);
    }
    @PutMapping(value = "/{showId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowDTO> updateShow(@PathVariable String showId, @RequestBody ShowDTO showDTO) {
      showService.updateShow(showId,showDTO);
    return new ResponseEntity<>( HttpStatus.OK);

    }
    @DeleteMapping(value = "{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable String showId) {
        showService.deleteShow(showId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
