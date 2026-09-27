package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.*;

@Component
@RequiredArgsConstructor
public class BookingRules {
    private final ShowDao showDao;
    private final BookingDao bookingDao;
    private final CurrentUser currentUser;
    public static final ZoneId SRI_LANKA = ZoneId.of("Asia/Colombo");

    public ShowEntity lockShow(String id) {
        return showDao.findForUpdate(id).orElseThrow(() -> new ResourceNotFoundException("Show not found"));
    }

    public BookingEntity lockBooking(String id) {
        String showId = bookingDao.findShowId(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        lockShow(showId);
        BookingEntity booking = bookingDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        currentUser.checkOwner(booking.getUser().getId());
        return booking;
    }

    public void checkBookable(ShowEntity show) {
        if (show.getStatus() != showStatus.SCHEDULED) { throw new BusinessException("Only scheduled shows can accept bookings or payments"); }
        if (!LocalDateTime.of(show.getShowDate(), show.getShowTime()).isAfter(LocalDateTime.now(SRI_LANKA))) {
            throw new BusinessException("The show has already started");
        }
        if (show.getTheatre().getStatus() != theatreStatus.ACTIVE) { throw new BusinessException("The theatre is inactive"); }
        if (show.getMovie().getStatus() == movieStatus.ENDED) { throw new BusinessException("The movie has ended"); }
    }

    public void cancel(BookingEntity booking) {
        booking.setStatus(bookingStatus.CANCELLED);
        PaymentEntity payment = booking.getPayment();
        if (payment != null && payment.getStatus() == paymentStatus.COMPLETED) {
            payment.setStatus(paymentStatus.REFUNDED);
        } else if (payment != null && payment.getStatus() == paymentStatus.PENDING) {
            payment.setStatus(paymentStatus.FAILED);
        }
        bookingDao.save(booking);
    }
}
