package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import jakarta.validation.Valid;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.showStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowDTO> create(@Valid @RequestBody ShowDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(showService.createShow(dto));
    }

    @GetMapping
    public ResponseEntity<List<ShowDTO>> getAll(@RequestParam(required = false) String movieId,
            @RequestParam(required = false) String theatreId, @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) showStatus status) {
        return ResponseEntity.ok(showService.getShows(movieId, theatreId, date, status));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDTO>> forMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(showService.getShowsForMovie(movieId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDTO> get(@PathVariable String id) { return ResponseEntity.ok(showService.getSelectedShow(id)); }

    @GetMapping("/{id}/seats")
    public ResponseEntity<SeatAvailabilityDTO> seats(@PathVariable String id) { return ResponseEntity.ok(showService.getSeats(id)); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowDTO> update(@PathVariable String id, @Valid @RequestBody ShowDTO dto) {
        return ResponseEntity.ok(showService.updateShow(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
