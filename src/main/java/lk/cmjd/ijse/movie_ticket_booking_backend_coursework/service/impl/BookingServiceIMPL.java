package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.BookingDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.bookingStatus;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class BookingServiceIMPL implements BookingService {

    @Override
    public void createBooking(BookingDTO booking) {
        System.out.println("create Booking: " + booking);

    }

    @Override
    public BookingDTO getselectedBooking(String bookingId) {
        return new BookingDTO(
                bookingId,
                "U001",
                "S001",
                "A5,A6",
                "2",
                3000.00,
                "2026-10-09",
                bookingStatus.CONFIRMED
        );
    }

    @Override
    public List<BookingDTO> getMyBookings() {
        List<BookingDTO> bookingsList = List.of(
                new BookingDTO(
                        "B001",
                        "U001",
                        "S001",
                        "A5,A6",
                        "2",
                        3000.00,
                        "2026-10-09",
                        bookingStatus.CONFIRMED
                ),
                new BookingDTO(
                        "B002",
                        "U001",
                        "S003",
                        "C7,C8",
                        "2",
                        4000.00,
                        "2026-10-09",
                        bookingStatus.PENDING
                ),
                new BookingDTO(
                        "B003",
                        "U001",
                        "S002",
                        "B3",
                        "1",
                        1200.00,
                        "2026-10-08",
                        bookingStatus.CANCELLED
                ));
        return bookingsList;
    }

    @Override
    public void cancelBooking(String bookingId, BookingDTO booking) {
        System.out.println("Canceled Booking id is : " + bookingId+ " and the booking is: "+booking.toString());


    }

}
