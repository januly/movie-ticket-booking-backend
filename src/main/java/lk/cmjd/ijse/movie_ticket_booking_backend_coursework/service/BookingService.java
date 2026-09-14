package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    void createBooking(BookingDTO booking);
    BookingDTO getselectedBooking(String bookingId);
    List<BookingDTO> getMyBookings();
    void cancelBooking(String bookingId, BookingDTO booking);

}
