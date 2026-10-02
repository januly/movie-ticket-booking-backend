package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import jakarta.validation.Valid;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BookingDTO> create(@Valid @RequestBody BookingDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingDTO>> getAll() { return ResponseEntity.ok(bookingService.getBookings()); }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingDTO>> myBookings() { return ResponseEntity.ok(bookingService.getMyBookings()); }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> get(@PathVariable String id) { return ResponseEntity.ok(bookingService.getSelectedBooking(id)); }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingDTO> cancel(@PathVariable String id) { return ResponseEntity.ok(bookingService.cancelBooking(id)); }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingDTO> updateStatus(@PathVariable String id, @Valid @RequestBody BookingStatusDTO dto) {
        return ResponseEntity.ok(bookingService.updateStatus(id, dto.getStatus()));
    }
}
