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
public class PaymentServiceIMPL implements PaymentService {
    private final PaymentDao paymentDao;
    private final BookingRules bookingRules;
    private final CurrentUser currentUser;
    private final Conversion conversion;

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        BookingEntity booking = bookingRules.lockBooking(dto.getBookingId());
        checkPayable(booking);
        if (paymentDao.existsByBookingBookingId(booking.getBookingId())) {
            throw new BusinessException("This booking already has a payment. Process the existing payment instead");
        }
        PaymentEntity payment = PaymentEntity.builder().paymentId(IDGenerate.paymentId()).booking(booking)
                .amount(booking.getTotalAmount()).paymentDate(LocalDateTime.now(BookingRules.SRI_LANKA))
                .paymentMethod(dto.getPaymentMethod()).status(paymentStatus.PENDING).build();
        log.info("Created payment {} for booking {}", payment.getPaymentId(), booking.getBookingId());
        return conversion.toPaymentDTO(paymentDao.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDTO getSelectedPayment(String id) { return conversion.toPaymentDTO(findOwnedPayment(id)); }

    @Override
    public PaymentDTO processPayment(String id, paymentStatus result) {
        if (result != paymentStatus.COMPLETED && result != paymentStatus.FAILED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment result must be COMPLETED or FAILED");
        }
        String showId = paymentDao.findShowId(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        bookingRules.lockShow(showId);
        PaymentEntity payment = findOwnedPayment(id);
        if (payment.getStatus() == paymentStatus.COMPLETED || payment.getStatus() == paymentStatus.REFUNDED) {
            throw new BusinessException("A completed or refunded payment cannot be processed again");
        }
        checkPayable(payment.getBooking());
        payment.setStatus(result);
        payment.setPaymentDate(LocalDateTime.now(BookingRules.SRI_LANKA));
        if (result == paymentStatus.COMPLETED) { payment.getBooking().setStatus(bookingStatus.CONFIRMED); }
        log.info("Processed payment {} with result {}", id, result);
        return conversion.toPaymentDTO(paymentDao.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public paymentStatus getPaymentStatus(String id) { return findOwnedPayment(id).getStatus(); }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getPayments() {
        return paymentDao.findAllByOrderByPaymentDateDesc().stream().map(conversion::toPaymentDTO).toList();
    }

    private PaymentEntity findOwnedPayment(String id) {
        PaymentEntity payment = paymentDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        currentUser.checkOwner(payment.getBooking().getUser().getId());
        return payment;
    }

    private void checkPayable(BookingEntity booking) {
        if (booking.getStatus() != bookingStatus.PENDING) { throw new BusinessException("Only pending bookings can be paid for"); }
        bookingRules.checkBookable(booking.getShow());
    }
}
