package lk.cmjd.ijse.movie_ticket_booking_backend_coursework.service;

import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.PaymentDTO;
import lk.cmjd.ijse.movie_ticket_booking_backend_coursework.dto.enums.paymentStatus;
import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO dto);
    PaymentDTO getSelectedPayment(String id);
    PaymentDTO processPayment(String id, paymentStatus result);
    paymentStatus getPaymentStatus(String id);
    List<PaymentDTO> getPayments();
}
