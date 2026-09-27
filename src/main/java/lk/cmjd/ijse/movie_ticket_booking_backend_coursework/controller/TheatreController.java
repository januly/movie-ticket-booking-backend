package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import jakarta.validation.Valid;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.TheatreDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.movieStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@RequiredArgsConstructor
public class TheatreController {
    private final TheatreService theatreService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TheatreDTO> create(@Valid @RequestBody TheatreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreService.createTheatre(dto));
    }

    @GetMapping
    public ResponseEntity<List<TheatreDTO>> getAll() {
        return ResponseEntity.ok(theatreService.getTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(theatreService.getSelectedTheatre(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TheatreDTO> update(@PathVariable String id, @Valid @RequestBody TheatreDTO dto) {
        return ResponseEntity.ok(theatreService.updateTheatre(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}
