package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.BookingDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.bookingStatus;
import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO dto);
    BookingDTO getSelectedBooking(String id);
    List<BookingDTO> getMyBookings();
    List<BookingDTO> getBookings();
    BookingDTO cancelBooking(String id);
    BookingDTO updateStatus(String id, bookingStatus status);
}
