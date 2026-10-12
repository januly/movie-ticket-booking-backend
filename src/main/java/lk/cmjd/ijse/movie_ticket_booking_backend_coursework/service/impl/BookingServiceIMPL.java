package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.impl;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dao.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.entities.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.exception.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.security.CurrentUser;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.*;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookingServiceIMPL implements BookingService {
    private final BookingDao bookingDao;
    private final CurrentUser currentUser;
    private final BookingRules bookingRules;
    private final SeatLayout seatLayout;
    private final Conversion conversion;

    @Override
    public BookingDTO createBooking(BookingDTO dto) {
        ShowEntity show = bookingRules.lockShow(dto.getShowId());
        bookingRules.checkBookable(show);
        UserEntity user = currentUser.get();
        if (user.getRole() != Role.CUSTOMER) { throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only customers can create bookings"); }
        List<String> seats = dto.getSeatNumbers().stream().map(seat -> seat.toUpperCase(Locale.ROOT)).toList();
        if (dto.getNumberOfTickets() != seats.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of tickets must match selected seats");
        }
        if (new HashSet<>(seats).size() != seats.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The same seat cannot be selected twice");
        }
        if (!seatLayout.seats(show.getTheatre().getCapacity()).containsAll(seats)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A selected seat does not exist in this theatre");
        }
        for (BookingEntity existing : bookingDao.findByShowShowIdAndStatusNot(show.getShowId(), bookingStatus.CANCELLED)) {
            List<String> reserved = Arrays.asList(existing.getSeatNumbers().split(","));
            if (seats.stream().anyMatch(reserved::contains)) { throw new BusinessException("A selected seat is already booked"); }
        }
        BookingEntity booking = BookingEntity.builder().bookingId(IDGenerate.bookingId()).user(user).show(show)
                .seatNumbers(String.join(",", seats)).numberOfTickets(seats.size())
                .totalAmount(show.getTicketPrice().multiply(BigDecimal.valueOf(seats.size())))
                .bookingDate(LocalDateTime.now(BookingRules.SRI_LANKA)).status(bookingStatus.PENDING).build();
        log.info("Customer {} created booking {} for show {}", user.getId(), booking.getBookingId(), show.getShowId());
        return conversion.toBookingDTO(bookingDao.save(booking));
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDTO getSelectedBooking(String id) { return conversion.toBookingDTO(findOwnedBooking(id)); }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getMyBookings() {
        return bookingDao.findByUserIdOrderByBookingDateDesc(currentUser.get().getId()).stream().map(conversion::toBookingDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getBookings() {
        return bookingDao.findAllByOrderByBookingDateDesc().stream().map(conversion::toBookingDTO).toList();
    }

    @Override
    public BookingDTO cancelBooking(String id) {
        BookingEntity booking = bookingRules.lockBooking(id);
        if (booking.getStatus() == bookingStatus.CANCELLED) { throw new BusinessException("This booking is already cancelled"); }
        if (currentUser.get().getRole() != Role.ADMIN
                && (booking.getShow().getStatus() == showStatus.COMPLETED
                || !LocalDateTime.of(booking.getShow().getShowDate(), booking.getShow().getShowTime())
                    .isAfter(LocalDateTime.now(BookingRules.SRI_LANKA)))) {
            throw new BusinessException("A booking cannot be cancelled after the show starts");
        }
        bookingRules.cancel(booking);
        log.info("Cancelled booking {}", id);
        return conversion.toBookingDTO(booking);
    }

    @Override
    public BookingDTO updateStatus(String id, bookingStatus status) {
        if (status == bookingStatus.CANCELLED) { return cancelBooking(id); }
        BookingEntity booking = bookingRules.lockBooking(id);
        if (booking.getStatus() == bookingStatus.CANCELLED) { throw new BusinessException("A cancelled booking cannot change status"); }
        if (status == bookingStatus.CONFIRMED
                && (booking.getPayment() == null || booking.getPayment().getStatus() != paymentStatus.COMPLETED)) {
            throw new BusinessException("A booking requires a completed payment before confirmation");
        }
        if (booking.getStatus() == bookingStatus.CONFIRMED && status == bookingStatus.PENDING) {
            throw new BusinessException("A confirmed booking cannot return to pending");
        }
        booking.setStatus(status);
        log.info("Updated booking {} to {}", id, status);
        return conversion.toBookingDTO(bookingDao.save(booking));
    }

    private BookingEntity findOwnedBooking(String id) {
        BookingEntity booking = bookingDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        currentUser.checkOwner(booking.getUser().getId());
        return booking;
    }
}
