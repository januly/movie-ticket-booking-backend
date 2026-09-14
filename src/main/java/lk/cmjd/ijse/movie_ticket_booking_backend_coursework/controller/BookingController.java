package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.controller;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.BookingDTO;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.bookingStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bookings")
@RestController
@RequiredArgsConstructor

public class BookingController {
    public  final BookingService bookingService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO> getSelectedBooking(@PathVariable String bookingId) {
        return new ResponseEntity<>(bookingService.getselectedBooking(bookingId), HttpStatus.OK);
    }

    @GetMapping(value = "/my-bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingDTO>> getMyBookings() {
        return new ResponseEntity<>(bookingService.getMyBookings(),HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable ("id") String bookingId,@RequestBody BookingDTO bookingDTO) {
        bookingService.cancelBooking(bookingId,bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

